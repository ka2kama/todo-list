package com.ka2kama.todolist.data.todo.dao

import anorm.SqlParser._
import anorm._
import com.ka2kama.todolist.data.todo.TodoDto
import javax.inject.Inject
import play.api.db.Database

import scala.concurrent.{ExecutionContext, Future}

private[data] final class TodoDaoByAnorm @Inject() (db: Database)(
    implicit ec: ExecutionContext
) extends TodoDao {

  private[this] val parser: RowParser[Long ~ String ~ Int] = long("id") ~ str(
    "content"
  ) ~ int("state")

  override def findAll: Future[Seq[TodoDto]] = Future {
    val todoAll = db.withConnection { implicit c =>
      SQL("Select * FROM todo")
        .as(parser.*)
        .iterator
        .map {
          case id ~ content ~ state => TodoDto(id, content, state)
        }
        .to(LazyList)
    }

    todoAll
  }

  override def findById(id: Long): Future[Option[TodoDto]] = Future {
    val todo = db.withConnection { implicit c =>
      val sql = SQL("SELECT * FROM todo WHERE id = {id}").on("id" -> id)
      sql.as(parser.singleOpt).map {
        case id ~ content ~ state => TodoDto(id, content, state)
      }
    }

    todo
  }
}
