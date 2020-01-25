package com.ka2kama.todolist.core.todo

import com.ka2kama.todolist.core.todo.domain.model.{Todo, TodoId}
import com.ka2kama.todolist.core.todo.domain.repository.TodoRepository
import com.typesafe.scalalogging.LazyLogging
import javax.inject.Inject

trait TodoService {
  def get(id: TodoId): Option[Todo]

  def list: Seq[Todo]
}

private[todo] final class TodoServiceImpl @Inject()(
  todoRepository: TodoRepository
) extends TodoService
    with LazyLogging {
  override def list: Seq[Todo] = {
    logger.info("todoService: list")
    todoRepository.findAll.to(LazyList)
  }

  override def get(id: TodoId): Option[Todo] = {
    logger.info("todoService: get")
    todoRepository.findById(id)
  }
}
