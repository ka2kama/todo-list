package com.ka2kama.todolist.core.domain.list

import cats.data.OptionT
import cats.implicits._
import com.ka2kama.todolist.core.domain.todo.model.TodoId
import com.ka2kama.todolist.core.domain.todo.repository.TodoRepository
import com.ka2kama.todolist.core.dto.todo.TodoDto
import com.typesafe.scalalogging.LazyLogging
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

trait ListService {
  def get(id: TodoId): OptionT[Future, TodoDto]

  def list: Future[Seq[TodoDto]]
}

private[core] final class ListServiceImpl @Inject() (
    todoRepository: TodoRepository
)(implicit ec: ExecutionContext)
    extends ListService
    with LazyLogging {
  override def list: Future[Seq[TodoDto]] = {
    logger.info("listService: list")
    val todosF = todoRepository.findAll
    todosF map (_ map TodoDto.toDto)
  }

  override def get(id: TodoId): OptionT[Future, TodoDto] = {
    logger.info("listService: get")
    val todo = todoRepository.findById(id)
    todo map TodoDto.toDto
  }
}
