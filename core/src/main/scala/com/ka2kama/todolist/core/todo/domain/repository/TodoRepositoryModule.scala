package com.ka2kama.todolist.core.todo.domain.repository

import com.ka2kama.todolist.core.db.todo.TodoDaoModule
import net.codingwell.scalaguice.ScalaPrivateModule

private[todo] class TodoRepositoryModule extends ScalaPrivateModule {
  override def configure(): Unit = {

    install(new TodoDaoModule)

    bind[TodoRepository].to[TodoRepositoryImpl]

    expose[TodoRepository]

    ()
  }
}
