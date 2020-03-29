package com.ka2kama.todolist.data.todo.dao

import com.ka2kama.todolist.data.todo.TodoDto
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.H2Profile.api._
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

private[data] final class TodoDaoBySlick @Inject() (
    protected val dbConfigProvider: DatabaseConfigProvider
) extends TodoDao
    with HasDatabaseConfigProvider[JdbcProfile] {

  private[this] val todos = TableQuery[Todos]

  override def findAll: Future[Seq[TodoDto]] = db.run(todos.result)

  override def findById(id: Long): Future[Option[TodoDto]] = {
    val q = todos.filter(_.id === id)
    db.run(q.result.headOption)
  }
}

private class Todos(tag: Tag) extends Table[TodoDto](tag, "todo") {
  def id      = column[Long]("id", O.PrimaryKey) // This is the primary key column
  def content = column[String]("content")

  def state = column[Int]("state")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, content, state) <> (TodoDto.tupled, TodoDto.unapply)
}
