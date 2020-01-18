package com.ka2kama.core.todo.domain.repository

import com.ka2kama.core.todo.domain.model.{Todo, TodoId}
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.H2Profile.api._
import slick.jdbc.JdbcProfile

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}
import scala.language.postfixOps

private[repository] class TodoRepositoryOnSlick @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ec: ExecutionContext)
    extends TodoRepository
    with HasDatabaseConfigProvider[JdbcProfile] {

  import dbConfig.profile.api._

  private val todos = TableQuery[Todos]

  private val toEntity: PartialFunction[(Long, String, Int), Todo] = {
    case (id, content, state) => Todo(TodoId(id), content, state)
  }

  override def findAll: Seq[Todo] = {
    val result = Await.result(db.run(todos.result), Duration.Inf)
    result.map(toEntity)
  }

  override def findById(id: TodoId): Option[Todo] = {
    val q = todos.filter(_.id === id.value)
    val result = Await.result(db.run(q.result.headOption), Duration.Inf)
    result.map(toEntity)
  }
}

private class Todos(tag: Tag) extends Table[(Long, String, Int)](tag, "todo") {
  def id = column[Long]("id", O.PrimaryKey) // This is the primary key column
  def content = column[String]("content")

  def state = column[Int]("state")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, content, state)
}
