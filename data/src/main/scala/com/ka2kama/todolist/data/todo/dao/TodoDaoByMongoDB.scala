package com.ka2kama.todolist.data.todo.dao

import com.ka2kama.todolist.data.todo.TodoRecord
import javax.inject.Inject
import monix.eval.Task
import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.modules.reactivemongo.{ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.Cursor
import reactivemongo.api.bson.BSONDocument
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.play.json.compat._

import scala.concurrent.{ExecutionContext, Future}

private[data] final class TodoDaoByMongoDB @Inject() (
    val reactiveMongoApi: ReactiveMongoApi
)(implicit ec: ExecutionContext)
    extends TodoDao
    with ReactiveMongoComponents {

  private[this] val todos: Future[BSONCollection] =
    reactiveMongoApi.database.map(_.collection[BSONCollection]("todo"))

  implicit private val todoReads: Reads[TodoRecord] = (
    (__ \ "id").read[Long] and
      (__ \ "content").read[String] and
      (__ \ "state").read[Int]
  )(TodoRecord.apply _)

  override def findAll: Task[Seq[TodoRecord]] = Task.deferFutureAction { implicit s =>
    val cursor = todos.map {
      _.find(BSONDocument(), None: Option[BSONDocument]).cursor[BSONDocument]()
    }
    val xs =
      cursor.flatMap(
        _.collect(-1, Cursor.FailOnError[Iterator[BSONDocument]]())
      )
    xs.map(_.map(_.as[TodoRecord]).to(LazyList))
  }

  override def findById(id: Long): Task[Option[TodoRecord]] = Task.deferFutureAction { implicit s =>
    val todo = todos.flatMap(
      _.find(BSONDocument("id" -> id), None: Option[BSONDocument])
        .one[TodoRecord]
    )
    todo
  }
}
