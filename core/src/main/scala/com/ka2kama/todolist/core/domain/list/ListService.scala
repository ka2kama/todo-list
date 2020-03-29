package com.ka2kama.todolist.core.domain.list

import cats.data.OptionT
import cats.implicits._
import com.ka2kama.todolist.core.datamodel.todo.TodoData
import com.ka2kama.todolist.core.domain.todo.model.TodoId
import com.ka2kama.todolist.core.domain.todo.repository.TodoRepository
import com.typesafe.scalalogging.LazyLogging
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

trait ListService {
  def get(id: TodoId): OptionT[Future, TodoData]

  def list: Future[Seq[TodoData]]
}

private[core] final class ListServiceImpl @Inject() (
    todoRepository: TodoRepository
)(implicit ec: ExecutionContext)
    extends ListService
    with LazyLogging {
  override def list: Future[Seq[TodoData]] = {
    logger.info("listService: list")
    val todosF = todoRepository.findAll
    todosF map (_ map TodoData.apply)
  }

  override def get(id: TodoId): OptionT[Future, TodoData] = {
    logger.info("listService: get")
    val todo = todoRepository.findById(id)
    todo map TodoData.apply
  }
}
