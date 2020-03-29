package com.ka2kama.todolist.data.todo

import com.ka2kama.todolist.data.todo.dao.{TodoDao, TodoDaoByAnorm}
import net.codingwell.scalaguice.ScalaPrivateModule

final class TodoDaoModule extends ScalaPrivateModule {
  override def configure(): Unit = {
    bind[TodoDao].to[TodoDaoByAnorm]
//    bind[TodoDao].to[TodoDaoByMongoDB]
//    bind[TodoDao].to[TodoDaoByScalikeJDBC]
//    bind[TodoDao].to[TodoDaoBySlick]
//    bind[TodoDao].to[TodoDaoOnMemory]

    expose[TodoDao]

    ()
  }
}
