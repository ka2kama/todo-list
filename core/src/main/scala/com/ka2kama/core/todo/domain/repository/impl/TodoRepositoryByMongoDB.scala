package com.ka2kama.core.todo.domain.repository.impl

import com.ka2kama.core.todo.domain.model.{Todo, TodoId}
import com.ka2kama.core.todo.domain.repository.TodoRepository
import javax.inject.Inject
import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.modules.reactivemongo.{ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.Cursor
import reactivemongo.api.bson.BSONDocument
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.play.json.compat._

import scala.collection.mutable.ListBuffer
import scala.collection.{Factory, IterableFactory, mutable}
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

private[repository] class TodoRepositoryByMongoDB @Inject()(
  val reactiveMongoApi: ReactiveMongoApi
)(implicit ec: ExecutionContext)
    extends TodoRepository
    with ReactiveMongoComponents {

  private val todos: Future[BSONCollection] =
    reactiveMongoApi.database.map(_.collection[BSONCollection]("todo"))

  implicit private val todoReads: Reads[Todo] = (
    (__ \ "id").read[Long].map(TodoId) and
      (__ \ "content").read[String] and
      (__ \ "state").read[Int]
  )(Todo.apply _)

  implicit private val cbf: Factory[BSONDocument, Seq[BSONDocument]] =
    new IterableFactory[Seq] {
      override def from[A](source: IterableOnce[A]): Seq[A] =
        source.iterator.toSeq

      override def empty[A]: Seq[A] = Nil

      override def newBuilder[A]: mutable.Builder[A, Seq[A]] = new ListBuffer
    }

  override def findAll: Seq[Todo] = {

    val cursor = todos.map {
      _.find(BSONDocument(), None: Option[BSONDocument]).cursor[BSONDocument]()
    }
    val xs =
      cursor.flatMap(_.collect(-1, Cursor.FailOnError[Seq[BSONDocument]]()))
    Await.result(xs, Duration.Inf).map(_.as[Todo])
  }

  override def findById(id: TodoId): Option[Todo] = {
    val todo = todos.flatMap(
      _.find(BSONDocument("id" -> id.value), None: Option[BSONDocument])
        .one[Todo]
    )
    Await.result(todo, Duration.Inf)
  }
}
