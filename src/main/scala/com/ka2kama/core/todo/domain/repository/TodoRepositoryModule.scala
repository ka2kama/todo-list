package com.ka2kama.core.todo.domain.repository

import net.codingwell.scalaguice.ScalaPrivateModule

private[todo] class TodoRepositoryModule extends ScalaPrivateModule {
  override def configure(): Unit = {
    bind[TodoRepository].to[TodoRepositoryOnMongoDB]

    expose[TodoRepository]
  }
}
