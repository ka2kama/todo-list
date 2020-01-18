package com.ka2kama.core.todo

import com.ka2kama.core.todo.domain.model.{Todo, TodoId}
import com.ka2kama.core.todo.domain.repository.TodoRepository
import javax.inject.Inject

trait TodoService {
  def get(id: TodoId): Option[Todo]

  def list: Seq[Todo]
}

private[todo] class TodoServiceImpl @Inject()(todoRepository: TodoRepository)
    extends TodoService {
  override def list: Seq[Todo] = todoRepository.findAll

  override def get(id: TodoId): Option[Todo] = todoRepository.findById(id)
}
