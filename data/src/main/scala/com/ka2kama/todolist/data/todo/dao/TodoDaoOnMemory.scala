package com.ka2kama.todolist.data.todo.dao

import com.ka2kama.todolist.data.todo.TodoDto
import monix.eval.Task

private[data] final class TodoDaoOnMemory extends TodoDao {
  private[this] val todosMap: Map[Long, TodoDto] = Map(
    1L -> TodoDto(1L, "掃除", 0),
    2L -> TodoDto(2L, "洗濯", 1),
    3L -> TodoDto(3L, "料理", 2)
  )

  override def findAll: Task[Seq[TodoDto]] = Task {
    val todos = todosMap.values.to(LazyList)
    todos
  }

  override def findById(id: Long): Task[Option[TodoDto]] = Task {
    val todoOption = todosMap.get(id)
    todoOption
  }
}
