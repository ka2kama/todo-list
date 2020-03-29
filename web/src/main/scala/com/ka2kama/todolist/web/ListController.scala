package com.ka2kama.todolist.web

import com.ka2kama.todolist.core.domain.list.ListService
import com.ka2kama.todolist.core.domain.todo.model.TodoId
import io.circe.generic.auto._
import io.circe.syntax._
import javax.inject.Inject
import monix.execution.Scheduler.Implicits.global
import play.api.Environment
import play.api.mvc._

final class ListController @Inject() (listService: ListService, cc: ControllerComponents)
    extends TodoBaseController(cc) {

  def list: Action[AnyContent] = ActionAsync {
    logger.info("list: ")

    val result = for {
      todos <- listService.list
    } yield todos.asJson

    result.map(Ok(_))
  }

  def get(id: Long): Action[AnyContent] = ActionAsync {
    logger.info("get: ")

    val todo     = listService.get(TodoId(id))
    val todoJson = todo.map(_.asJson)

    todoJson.fold(NotFound: Result)(Ok(_))
  }

  def stream: Action[AnyContent] = Action {
    logger.info("stream: ")

    //implicit val system: ActorSystem = ActorSystem("QuickStart")
    //implicit val ec: ExecutionContextExecutor = system.dispatcher

//    val source = Source(1 to 3000)
//    //val sink       = Sink.fold[Int, Int](0)(_ + _)
//    val factorials = source.scan(BigInt(1))((acc, next) => acc * next)
//    val result = factorials
//      .zipWith(Source(0 to 10000))((num, idx) => s"$idx! = $num\n")
//      .map(ByteString(_))
//    //.throttle(1, 1.second)
//
//    Ok.streamed(result, None, inline = false, Some("test.txt"))
//    val sum = source.runWith(sink)
//
//    sum.map(Ok(_))
//    val file                          = new java.io.File("/tmp/fileToServe.pdf")
//    val path: java.nio.file.Path      = file.toPath
//    val source: Source[ByteString, _] = FileIO.fromPath(path)
//
//    Result(
//      header = ResponseHeader(200, Map.empty),
//      body = HttpEntity.Streamed(source, None, Some("application/pdf"))
//    )
    Ok.sendFile(Environment.simple().getFile("/tmp/fileToServe.pdf"))
  }
}
