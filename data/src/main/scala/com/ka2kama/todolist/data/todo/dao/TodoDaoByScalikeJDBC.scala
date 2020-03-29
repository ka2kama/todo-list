package com.ka2kama.todolist.data.todo.dao

import com.ka2kama.todolist.data.todo.TodoDto
import monix.eval.Task
import scalikejdbc._

private[data] final class TodoDaoByScalikeJDBC extends TodoDao {

  override def findAll: Task[Seq[TodoDto]] = Task {
    val todos = DB readOnly { implicit session =>
      sql"select * from todo".map(TodoSupport.apply).list.apply()
    }

    todos
  }

  override def findById(id: Long): Task[Option[TodoDto]] = Task {
    val todo = DB readOnly { implicit session =>
      sql"select * from todo where id = $id"
        .map(TodoSupport.apply)
        .single
        .apply()
    }

    todo
  }
}

private object TodoSupport extends SQLSyntaxSupport[TodoDto] {
  override val tableName = "todo"
  def apply(rs: WrappedResultSet): TodoDto =
    TodoDto(rs.long("id"), rs.string("content"), rs.int("state"))
}
