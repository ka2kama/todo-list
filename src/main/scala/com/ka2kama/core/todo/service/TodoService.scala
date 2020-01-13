package com.ka2kama.core.todo.service

import com.ka2kama.core.todo.domain.model.Todo
import com.ka2kama.core.todo.domain.repository.TodoRepository
import javax.inject.Inject

trait TodoService {
  def list: Seq[Todo]
}

private[todo] class TodoServiceImpl @Inject()(todoRepository: TodoRepository)
    extends TodoService {
  override def list: Seq[Todo] = todoRepository.findAll
}
