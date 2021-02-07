package com.ka2kama.todolist.data

import com.ka2kama.todolist.data.todo.dao.{TodoDao, TodoDaoBySlick}
import net.codingwell.scalaguice.ScalaPrivateModule

final class DataModule extends ScalaPrivateModule {
  override def configure(): Unit = {
    bind[TodoDao].to[TodoDaoBySlick]

    expose[TodoDao]

    ()
  }
}
