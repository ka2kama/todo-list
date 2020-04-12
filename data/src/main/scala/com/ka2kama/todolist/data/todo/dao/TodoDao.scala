package com.ka2kama.todolist.data.todo.dao

import com.ka2kama.todolist.data.todo.TodoRecord
import monix.eval.Task

trait TodoDao {
  def findAll: Task[Seq[TodoRecord]]

  def findById(id: Long): Task[Option[TodoRecord]]
}
