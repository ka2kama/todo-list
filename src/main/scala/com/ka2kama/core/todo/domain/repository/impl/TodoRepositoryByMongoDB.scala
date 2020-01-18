package com.ka2kama.core.todo.domain.repository.impl

import com.ka2kama.core.todo.domain.model.{Todo, TodoId}
import com.ka2kama.core.todo.domain.repository.TodoRepository
import javax.inject.Inject
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsObject, Json, _}
import play.modules.reactivemongo.{ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.Cursor
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection
import reactivemongo.play.json.compat._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

private[repository] class TodoRepositoryByMongoDB @Inject()(
  val reactiveMongoApi: ReactiveMongoApi
)(implicit ec: ExecutionContext)
    extends TodoRepository
    with ReactiveMongoComponents {

  private val todos: Future[JSONCollection] =
    reactiveMongoApi.database.map(_.collection[JSONCollection]("todo"))

  implicit private val todoReads: Reads[Todo] = (
    (__ \ "id").read[Long].map(TodoId) and
      (__ \ "content").read[String] and
      (__ \ "state").read[Int]
  )(Todo.apply _)

  override def findAll: Seq[Todo] = {
    val cursor = todos.map {
      _.find(Json.obj(), None: Option[JsObject]).cursor[JsObject]()
    }
    val xs =
      cursor.flatMap(_.collect(-1, Cursor.FailOnError[Seq[JsObject]]()))
    Await.result(xs, Duration.Inf).map(_.as[Todo])
  }

  override def findById(id: TodoId): Option[Todo] = {
    val todo = todos.flatMap(
      _.find(Json.obj("id" -> id.value), None: Option[JsObject]).one[Todo]
    )
    Await.result(todo, Duration.Inf)
  }
}
