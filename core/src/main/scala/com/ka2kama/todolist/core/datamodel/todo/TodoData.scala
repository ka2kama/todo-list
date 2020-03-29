package com.ka2kama.todolist.core.datamodel.todo

import com.ka2kama.todolist.core.domain.todo.model.Todo

final case class TodoData(id: Long, content: String, state: Int)

object TodoData {
  def apply(todo: Todo): TodoData =
    TodoData(todo.id.value, todo.content.value, todo.state.value)
}
