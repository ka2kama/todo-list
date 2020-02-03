package com.ka2kama.todolist.core.domain.todo.repository

import com.ka2kama.todolist.core.db.todo.TodoDaoModule
import net.codingwell.scalaguice.ScalaPrivateModule

private[core] class TodoRepositoryModule extends ScalaPrivateModule {
  override def configure(): Unit = {

    install(new TodoDaoModule)

    bind[TodoRepository].to[TodoRepositoryImpl]

    expose[TodoRepository]

    ()
  }
}
