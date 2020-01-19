package com.ka2kama.todolist.core.db.todo.dao

import anorm.SqlParser._
import anorm._
import com.ka2kama.todolist.core.db.todo.TodoDto
import javax.inject.Inject
import play.api.db.Database

private[todo] class TodoDaoByAnorm @Inject()(db: Database) extends TodoDao {

  private val parser = long("id") ~ str("content") ~ int("state")

  override def findAll: Seq[TodoDto] = db.withConnection { implicit c =>
    SQL("Select * FROM todo")
      .as(parser.*)
      .iterator
      .map {
        case id ~ content ~ state => TodoDto(id, content, state)
      }
      .to(LazyList)
  }

  override def findById(id: Long): Option[TodoDto] = db.withConnection {
    implicit c =>
      val sql = SQL("SELECT * FROM todo WHERE id = {id}").on("id" -> id)
      sql.as(parser.singleOpt).map {
        case id ~ content ~ state => TodoDto(id, content, state)
      }
  }
}
