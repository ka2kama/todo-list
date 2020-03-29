package com.ka2kama.todolist.data.todo.dao

import anorm.SqlParser._
import anorm._
import com.ka2kama.todolist.data.todo.TodoDto
import javax.inject.Inject
import monix.eval.Task
import play.api.db.Database

private[data] final class TodoDaoByAnorm @Inject() (db: Database) extends TodoDao {

  private[this] val parser: RowParser[Long ~ String ~ Int] = long("id") ~ str(
    "content"
  ) ~ int("state")

  override def findAll: Task[Seq[TodoDto]] = Task {
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

  override def findById(id: Long): Task[Option[TodoDto]] = Task {
    val todo = db.withConnection { implicit c =>
      val sql = SQL("SELECT * FROM todo WHERE id = {id}").on("id" -> id)
      sql.as(parser.singleOpt).map {
        case id ~ content ~ state => TodoDto(id, content, state)
      }
    }

    todo
  }
}
