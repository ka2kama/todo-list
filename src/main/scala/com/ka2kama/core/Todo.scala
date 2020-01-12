package com.ka2kama.core

import scala.reflect.{ClassTag, classTag}

class Todo(val id: TodoId, val content: String, val state: Int) extends Entity {
  override type EntityType = Todo
  override type IdType = TodoId
  override protected val tag: ClassTag[Todo] = classTag[Todo]
}

case class TodoId(value: Long) extends EntityLongId
