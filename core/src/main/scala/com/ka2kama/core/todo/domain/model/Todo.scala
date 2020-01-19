package com.ka2kama.core.todo.domain.model

import com.ka2kama.core.support.{Entity, EntityLongId}

import scala.reflect.{ClassTag, classTag}

case class Todo(id: TodoId, content: String, state: Int) extends Entity {
  override type EntityType = Todo
  override type IdType = TodoId
  override protected val tag: ClassTag[Todo] = classTag[Todo]
}
case class TodoId(value: Long) extends EntityLongId
