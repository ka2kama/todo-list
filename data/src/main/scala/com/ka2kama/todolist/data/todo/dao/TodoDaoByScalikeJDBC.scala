package com.ka2kama.todolist.data.todo.dao

import com.ka2kama.todolist.data.todo.TodoRecord
import monix.eval.Task
import scalikejdbc._

private[data] final class TodoDaoByScalikeJDBC extends TodoDao {

  override def findAll: Task[Seq[TodoRecord]] = Task {
    val todos = DB readOnly { implicit session =>
      sql"select * from todo".map(TodoSupport.apply).list().apply()
    }

    todos
  }

  override def findById(id: Long): Task[Option[TodoRecord]] = Task {
    val todo = DB readOnly { implicit session =>
      sql"select * from todo where id = $id"
        .map(TodoSupport.apply)
        .single()
        .apply()
    }

    todo
  }
}

private object TodoSupport extends SQLSyntaxSupport[TodoRecord] {
  override val tableName = "todo"
  def apply(rs: WrappedResultSet): TodoRecord =
    TodoRecord(rs.long("id"), rs.string("content"), rs.int("state"))
}
