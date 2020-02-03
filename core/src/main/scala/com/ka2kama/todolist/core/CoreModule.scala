package com.ka2kama.todolist.core

import com.ka2kama.todolist.core.domain.list.{ListService, ListServiceImpl}
import com.ka2kama.todolist.core.domain.todo.repository.TodoRepositoryModule
import net.codingwell.scalaguice.ScalaPrivateModule

class CoreModule extends ScalaPrivateModule {
  override def configure(): Unit = {

    install(new TodoRepositoryModule)

    bind[ListService].to[ListServiceImpl]

    expose[ListService]

    ()
  }
}
