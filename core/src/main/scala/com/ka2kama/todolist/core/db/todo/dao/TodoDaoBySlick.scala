package com.ka2kama.todolist.core.db.todo.dao

import com.ka2kama.todolist.core.db.todo.TodoDto
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.H2Profile.api._
import slick.jdbc.JdbcProfile

import scala.concurrent.Await
import scala.concurrent.duration.Duration

private[todo] class TodoDaoBySlick @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
) extends TodoDao
    with HasDatabaseConfigProvider[JdbcProfile] {

  private val todos = TableQuery[Todos]

  override def findAll: Seq[TodoDto] = {
    val result = Await.result(db.run(todos.result), Duration.Inf)
    result
  }

  override def findById(id: Long): Option[TodoDto] = {
    val q = todos.filter(_.id === id)
    val result = Await.result(db.run(q.result.headOption), Duration.Inf)
    result
  }
}

private class Todos(tag: Tag) extends Table[TodoDto](tag, "todo") {
  def id = column[Long]("id", O.PrimaryKey) // This is the primary key column
  def content = column[String]("content")

  def state = column[Int]("state")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, content, state) <> (TodoDto.tupled, TodoDto.unapply)
}
