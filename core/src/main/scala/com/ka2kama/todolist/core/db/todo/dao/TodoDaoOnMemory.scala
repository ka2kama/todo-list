package com.ka2kama.todolist.core.db.todo.dao

import com.ka2kama.todolist.core.db.todo.TodoDto

private[todo] final class TodoDaoOnMemory extends TodoDao {
  private[this] val todos: Map[Long, TodoDto] = Map(
    1L -> TodoDto(1L, "掃除", 0),
    2L -> TodoDto(2L, "洗濯", 1),
    3L -> TodoDto(3L, "料理", 2),
  )

  override def findAll: Seq[TodoDto] = todos.values.to(LazyList)

  override def findById(id: Long): Option[TodoDto] = todos.get(id)
}
