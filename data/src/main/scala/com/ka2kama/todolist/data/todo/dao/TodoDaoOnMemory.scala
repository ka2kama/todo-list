package com.ka2kama.todolist.data.todo.dao

import com.ka2kama.todolist.data.todo.TodoRecord
import monix.eval.Task

private[data] final class TodoDaoOnMemory extends TodoDao {
  private[this] val todosMap: Map[Long, TodoRecord] = Map(
    1L -> TodoRecord(1L, "掃除", 0),
    2L -> TodoRecord(2L, "洗濯", 1),
    3L -> TodoRecord(3L, "料理", 2),
  )

  override def findAll: Task[Seq[TodoRecord]] = Task {
    todosMap.values.toList
  }

  override def findById(id: Long): Task[Option[TodoRecord]] = Task {
    todosMap.get(id)
  }
}
