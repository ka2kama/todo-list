package com.ka2kama.todolist.core.todo.domain.model

import com.ka2kama.todolist.core.support.Entity

import scala.reflect.{ClassTag, classTag}

final case class Todo(id: TodoId, content: Content, state: State) extends Entity {
  override type EntityType = Todo
  override type IdType     = TodoId
  override protected val tag: ClassTag[Todo] = classTag[Todo]
}
