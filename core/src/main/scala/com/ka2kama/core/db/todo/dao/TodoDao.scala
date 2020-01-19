package com.ka2kama.core.db.todo.dao

import com.ka2kama.core.db.todo.TodoDto

private[core] trait TodoDao {
  def findAll: Seq[TodoDto]

  def findById(id: Long): Option[TodoDto]
}
