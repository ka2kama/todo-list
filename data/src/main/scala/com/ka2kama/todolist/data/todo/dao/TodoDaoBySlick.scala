package com.ka2kama.todolist.data.todo.dao

import com.ka2kama.todolist.data.todo.TodoRecord
import monix.eval.Task
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig, HasDatabaseConfigProvider}
import slick.dbio.{DBIOAction, NoStream}
import slick.jdbc.H2Profile.api._
import slick.jdbc.JdbcProfile

import javax.inject.Inject

private[data] final class TodoDaoBySlick @Inject() (
    protected val dbConfigProvider: DatabaseConfigProvider
) extends TodoDao
    with HasDatabaseConfigProvider[JdbcProfile]
    with SlickOps {
  import Todos._

  override def findAll: Task[Seq[TodoRecord]] = deferRun(todos.result)

  override def findById(id: Long): Task[Option[TodoRecord]] = {
    val q = todos.filter(_.id === id)
    deferRun(q.result.headOption)
  }
}

private final class Todos(tag: Tag) extends Table[TodoRecord](tag, "todo") {
  def id      = column[Long]("id", O.PrimaryKey) // This is the primary key column
  def content = column[String]("content")

  def state = column[Int]("state")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, content, state).<>(TodoRecord.tupled, TodoRecord.unapply)
}
private object Todos {
  val todos: TableQuery[Todos] = TableQuery[Todos]
}

trait SlickOps { self: HasDatabaseConfig[_] =>
  final def deferRun[R](a: DBIOAction[R, NoStream, Nothing]): Task[R] = Task.deferFuture {
    db.run(a)
  }
}
