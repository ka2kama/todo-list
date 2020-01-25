package com.ka2kama.todolist.core.db.todo.dao

import com.ka2kama.todolist.core.db.todo.TodoDto
import javax.inject.Inject
import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.modules.reactivemongo.{ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.Cursor
import reactivemongo.api.bson.BSONDocument
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.play.json.compat._

import scala.concurrent.{ExecutionContext, Future}

private[todo] final class TodoDaoByMongoDB @Inject() (
    val reactiveMongoApi: ReactiveMongoApi
)(implicit ec: ExecutionContext)
    extends TodoDao
    with ReactiveMongoComponents {

  private[this] val todos: Future[BSONCollection] =
    reactiveMongoApi.database.map(_.collection[BSONCollection]("todo"))

  implicit private val todoReads: Reads[TodoDto] = (
    (__ \ "id").read[Long] and
      (__ \ "content").read[String] and
      (__ \ "state").read[Int]
  )(TodoDto.apply _)

  override def findAll: Future[Seq[TodoDto]] = {

    val cursor = todos.map {
      _.find(BSONDocument(), None: Option[BSONDocument]).cursor[BSONDocument]()
    }
    val xs =
      cursor.flatMap(
        _.collect(-1, Cursor.FailOnError[Iterator[BSONDocument]]())
      )
    xs.map(_.map(_.as[TodoDto]).to(LazyList))
  }

  override def findById(id: Long): Future[Option[TodoDto]] = {
    val todo = todos.flatMap(
      _.find(BSONDocument("id" -> id), None: Option[BSONDocument])
        .one[TodoDto]
    )
    todo
  }
}
