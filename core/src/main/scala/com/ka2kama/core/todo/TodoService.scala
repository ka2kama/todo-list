package com.ka2kama.core.todo

import com.ka2kama.core.todo.domain.model.{Todo, TodoId}
import com.ka2kama.core.todo.domain.repository.TodoRepository
import com.typesafe.scalalogging.LazyLogging
import javax.inject.Inject

trait TodoService {
  def get(id: TodoId): Option[Todo]

  def list: Seq[Todo]
}

private[todo] class TodoServiceImpl @Inject()(todoRepository: TodoRepository)
    extends TodoService
    with LazyLogging {
  override def list: Seq[Todo] = {
    logger.info("todoService: list")
    todoRepository.findAll
  }

  override def get(id: TodoId): Option[Todo] = {
    logger.info("todoService: get")
    todoRepository.findById(id)
  }
}
