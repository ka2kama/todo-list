package com.ka2kama.todolist.core.dto.todo

import com.ka2kama.todolist.core.domain.todo.model.Todo

final case class TodoDto(id: Long, content: String, state: Int)

object TodoDto {
  def apply(todo: Todo): TodoDto = TodoDto(todo.id.value, todo.content.value, todo.state.value)
}
