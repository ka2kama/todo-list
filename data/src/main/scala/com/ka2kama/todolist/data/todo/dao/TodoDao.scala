package com.ka2kama.todolist.data.todo.dao

import com.ka2kama.todolist.data.todo.TodoDto
import monix.eval.Task

trait TodoDao {
  def findAll: Task[Seq[TodoDto]]

  def findById(id: Long): Task[Option[TodoDto]]
}
