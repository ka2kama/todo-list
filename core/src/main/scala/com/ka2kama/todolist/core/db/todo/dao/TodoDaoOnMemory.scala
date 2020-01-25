package com.ka2kama.todolist.core.db.todo.dao

import com.ka2kama.todolist.core.db.todo.TodoDto

import scala.concurrent.Future

private[todo] final class TodoDaoOnMemory extends TodoDao {
  private[this] val todosMap: Map[Long, TodoDto] = Map(
    1L -> TodoDto(1L, "掃除", 0),
    2L -> TodoDto(2L, "洗濯", 1),
    3L -> TodoDto(3L, "料理", 2)
  )

  override def findAll: Future[Seq[TodoDto]] = {
    val todos = todosMap.values.to(LazyList)
    Future.successful(todos)
  }

  override def findById(id: Long): Future[Option[TodoDto]] = {
    val todoOption = todosMap.get(id)
    Future.successful(todoOption)
  }
}
