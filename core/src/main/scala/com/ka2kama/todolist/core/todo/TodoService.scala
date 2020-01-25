package com.ka2kama.todolist.core.todo

import com.ka2kama.todolist.core.todo.domain.model.{Todo, TodoId}
import com.ka2kama.todolist.core.todo.domain.repository.TodoRepository
import com.typesafe.scalalogging.LazyLogging
import javax.inject.Inject

import scala.concurrent.Future

trait TodoService {
  def get(id: TodoId): Future[Option[Todo]]

  def list: Future[Seq[Todo]]
}

private[todo] final class TodoServiceImpl @Inject()(
  todoRepository: TodoRepository
) extends TodoService
    with LazyLogging {
  override def list: Future[Seq[Todo]] = {
    logger.info("todoService: list")
    todoRepository.findAll
  }

  override def get(id: TodoId): Future[Option[Todo]] = {
    logger.info("todoService: get")
    todoRepository.findById(id)
  }
}
