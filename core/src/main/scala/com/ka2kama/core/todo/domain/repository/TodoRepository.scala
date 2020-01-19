package com.ka2kama.core.todo.domain.repository

import com.ka2kama.core.support.Repository
import com.ka2kama.core.todo.domain.model.Todo

private[todo] trait TodoRepository extends Repository {
  override type E = Todo
}
