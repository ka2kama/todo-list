package com.ka2kama.core.todo

import com.ka2kama.core.todo.domain.repository.TodoRepository
import com.ka2kama.core.todo.service.{TodoService, TodoServiceImpl}
import net.codingwell.scalaguice.ScalaPrivateModule

class TodoModule extends ScalaPrivateModule {
  override def configure(): Unit = {
    bind[TodoRepository].toInstance(TodoRepository.onMemory)
    bind[TodoService].to[TodoServiceImpl]

    expose[TodoService]
  }
}
