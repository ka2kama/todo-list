package com.ka2kama.core.todo.domain.repository

import com.ka2kama.core.todo.domain.model.Todo

private[todo] trait TodoRepository {
  def findAll: Seq[Todo]
}

private[todo] object TodoRepository {
  def onMemory: TodoRepository = new TodoRepositoryOnMemory
}
