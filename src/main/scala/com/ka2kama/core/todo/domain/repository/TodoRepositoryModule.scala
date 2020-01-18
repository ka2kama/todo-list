package com.ka2kama.core.todo.domain.repository

import com.ka2kama.core.todo.domain.repository.impl.TodoRepositoryByMongoDB
import net.codingwell.scalaguice.ScalaPrivateModule

private[todo] class TodoRepositoryModule extends ScalaPrivateModule {
  override def configure(): Unit = {
    bind[TodoRepository].to[TodoRepositoryByMongoDB]

    expose[TodoRepository]
  }
}
