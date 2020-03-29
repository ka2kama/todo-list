package com.ka2kama.todolist.core

import com.ka2kama.todolist.core.domain.list.{ListService, ListServiceImpl}
import com.ka2kama.todolist.core.domain.todo.repository.{TodoRepository, TodoRepositoryImpl}
import com.ka2kama.todolist.data.DataModule
import net.codingwell.scalaguice.ScalaPrivateModule

class CoreModule extends ScalaPrivateModule {
  override def configure(): Unit = {

    installRepositories()

    bind[ListService].to[ListServiceImpl]
    expose[ListService]

    ()
  }

  def installRepositories(): Unit = {
    install(new DataModule)

    bind[TodoRepository].to[TodoRepositoryImpl]

    ()
  }
}
