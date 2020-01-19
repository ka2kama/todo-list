package com.ka2kama.todolist.core.todo

import com.ka2kama.todolist.core.todo.domain.repository.TodoRepositoryModule
import net.codingwell.scalaguice.ScalaPrivateModule

class TodoServiceModule extends ScalaPrivateModule {
  override def configure(): Unit = {

    install(new TodoRepositoryModule)

    bind[TodoService].to[TodoServiceImpl]

    expose[TodoService]

    ()
  }
}
