package com.ka2kama.core.todo.domain.repository
import anorm.SqlParser._
import anorm._
import com.ka2kama.core.todo.domain.model.{Todo, TodoId}
import javax.inject.Inject
import play.api.db.Database

private[repository] class TodoRepositoryOnAnorm @Inject()(db: Database)
    extends TodoRepository {

  override def findAll: Seq[Todo] = db.withConnection { implicit c =>
    val parser = long("id") ~ str("content") ~ int("state")
    val result = SQL("Select * FROM todo").as(parser.*)
    result.iterator.map {
      case id ~ content ~ state => Todo(TodoId(id), content, state)
    }.toSeq
  }
}
