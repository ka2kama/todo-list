package com.ka2kama.core.todo.domain.repository
import com.ka2kama.core.todo.domain.model.{Todo, TodoId}

private[repository] class TodoRepositoryOnMemory extends TodoRepository {
  private val todos = Seq(
    Todo(TodoId(1), "掃除", 0),
    Todo(TodoId(2), "洗濯", 1),
    Todo(TodoId(3), "料理", 2),
  )

  override def findAll: Seq[Todo] = todos
}
