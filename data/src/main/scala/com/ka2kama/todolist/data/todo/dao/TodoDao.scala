package com.ka2kama.todolist.data.todo.dao

import com.ka2kama.todolist.data.todo.TodoDto

import scala.concurrent.Future

trait TodoDao {
  def findAll: Future[Seq[TodoDto]]

  def findById(id: Long): Future[Option[TodoDto]]
}
