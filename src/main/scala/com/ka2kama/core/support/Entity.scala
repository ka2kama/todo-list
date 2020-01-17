package com.ka2kama.core.support

import scala.reflect.ClassTag

trait Entity {
  type EntityType <: Entity
  type IdType <: EntityId
  val id: IdType

  protected val tag: ClassTag[EntityType]

  def canEqual(other: Any): Boolean = tag.runtimeClass.isInstance(other)

  override def equals(other: Any): Boolean = other match {
    case tag(that) => (that canEqual this) && id == that.id
    case _         => false
  }

  override def hashCode(): Int = 31 * id.##
}

trait EntityId {
  type IdType
  def value: IdType
}

trait EntityLongId extends EntityId {
  override type IdType = Long
}
