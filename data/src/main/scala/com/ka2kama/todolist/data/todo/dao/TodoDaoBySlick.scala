package com.ka2kama.todolist.data.todo.dao

import com.ka2kama.todolist.data.todo.TodoRecord
import javax.inject.Inject
import monix.eval.Task
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.H2Profile.api._
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

private[data] final class TodoDaoBySlick @Inject() (
    protected val dbConfigProvider: DatabaseConfigProvider
) extends TodoDao
    with HasDatabaseConfigProvider[JdbcProfile] {

  private[this] val todos = TableQuery[Todos]

  override def findAll: Task[Seq[TodoRecord]] = Task.deferFuture { db.run(todos.result) }

  override def findById(id: Long): Task[Option[TodoRecord]] = Task.deferFuture {
    val q = todos.filter(_.id === id)
    db.run(q.result.headOption)
  }
}

private class Todos(tag: Tag) extends Table[TodoRecord](tag, "todo") {
  def id: Rep[Long]        = column[Long]("id", O.PrimaryKey) // This is the primary key column
  def content: Rep[String] = column[String]("content")

  def state: Rep[Int] = column[Int]("state")

  // Every table needs a * projection with the same type as the table's type parameter
  def * : ProvenShape[TodoRecord] = (id, content, state).mapTo[TodoRecord]
}
