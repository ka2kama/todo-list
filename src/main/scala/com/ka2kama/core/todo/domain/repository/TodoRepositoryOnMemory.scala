package com.ka2kama.core.todo.domain.repository
import com.ka2kama.core.todo.domain.model.{Todo, TodoId}

private[repository] class TodoRepositoryOnMemory extends TodoRepository {
  private val todos = Map(
    TodoId(1) -> Todo(TodoId(1), "掃除", 0),
    TodoId(2) -> Todo(TodoId(2), "洗濯", 1),
    TodoId(3) -> Todo(TodoId(3), "料理", 2),
  )

  override def findAll: Seq[Todo] = todos.values.toSeq

  override def findById(id: TodoId): Option[Todo] = todos.get(id)
}
