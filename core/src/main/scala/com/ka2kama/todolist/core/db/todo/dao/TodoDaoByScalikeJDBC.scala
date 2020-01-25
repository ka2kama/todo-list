package com.ka2kama.todolist.core.db.todo.dao

import com.ka2kama.todolist.core.db.todo.TodoDto
import scalikejdbc._

import scala.concurrent.{ExecutionContext, Future}

private[todo] final class TodoDaoByScalikeJDBC(implicit ec: ExecutionContext) extends TodoDao {

  override def findAll: Future[Seq[TodoDto]] = Future {
    val todos = DB readOnly { implicit session =>
      sql"select * from todo".map(TodoSupport.apply).list.apply()
    }

    todos
  }

  override def findById(id: Long): Future[Option[TodoDto]] = Future {
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
