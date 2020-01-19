package com.ka2kama.core.todo.domain.repository

import com.ka2kama.core.todo.domain.repository.impl.TodoRepositoryByScalikeJDBC
import net.codingwell.scalaguice.ScalaPrivateModule

private[todo] class TodoRepositoryModule extends ScalaPrivateModule {
  override def configure(): Unit = {
//    bind[TodoRepository].to[TodoRepositoryByAnorm]
//    bind[TodoRepository].to[TodoRepositoryByMongoDB]
    bind[TodoRepository].to[TodoRepositoryByScalikeJDBC]
//    bind[TodoRepository].to[TodoRepositoryBySlick]
//    bind[TodoRepository].to[TodoRepositoryOnMemory]

    expose[TodoRepository]

    ()
  }
}
