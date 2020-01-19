package com.ka2kama.core.support

private[core] trait Repository {
  type E <: Entity

  def findAll: Iterator[E]

  def findById(id: E#IdType): Option[E]
}
