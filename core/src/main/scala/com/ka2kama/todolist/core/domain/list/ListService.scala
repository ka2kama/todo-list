package com.ka2kama.todolist.core.domain.list

import cats.data.OptionT
import com.ka2kama.todolist.core.domain.todo.model.{Todo, TodoId}
import com.ka2kama.todolist.core.domain.todo.repository.TodoRepository
import com.typesafe.scalalogging.LazyLogging
import javax.inject.Inject

import scala.concurrent.Future

trait ListService {
  def get(id: TodoId): OptionT[Future, Todo]

  def list: Future[Seq[Todo]]
}

private[core] final class ListServiceImpl @Inject() (
    todoRepository: TodoRepository
) extends ListService
    with LazyLogging {
  override def list: Future[Seq[Todo]] = {
    logger.info("listService: list")
    todoRepository.findAll
  }

  override def get(id: TodoId): OptionT[Future, Todo] = {
    logger.info("listService: get")
    todoRepository.findById(id)
  }
}
