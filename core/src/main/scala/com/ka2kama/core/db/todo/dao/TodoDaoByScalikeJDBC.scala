package com.ka2kama.core.db.todo.dao

import com.ka2kama.core.db.todo.TodoDto
import scalikejdbc._

private[todo] class TodoDaoByScalikeJDBC extends TodoDao {

  override def findAll: Seq[TodoDto] = DB readOnly { implicit session =>
    sql"select * from todo".map(TodoSupport.apply).list.apply()
  }

  override def findById(id: Long): Option[TodoDto] = DB readOnly {
    implicit session =>
      sql"select * from todo where id = $id"
        .map(TodoSupport.apply)
        .single
        .apply()
  }
}

private object TodoSupport extends SQLSyntaxSupport[TodoDto] {
  override val tableName = "todo"
  def apply(rs: WrappedResultSet): TodoDto =
    TodoDto(rs.long("id"), rs.string("content"), rs.int("state"))
}
