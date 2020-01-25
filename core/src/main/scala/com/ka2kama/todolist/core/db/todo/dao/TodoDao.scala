package com.ka2kama.todolist.core.db.todo.dao

import com.ka2kama.todolist.core.db.todo.TodoDto

import scala.concurrent.Future

private[core] trait TodoDao {
  def findAll: Future[Seq[TodoDto]]

  def findById(id: Long): Future[Option[TodoDto]]
}
