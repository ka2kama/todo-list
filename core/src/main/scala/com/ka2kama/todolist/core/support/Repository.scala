package com.ka2kama.todolist.core.support

private[core] trait Repository {
  type E <: Entity

  def findAll: Seq[E]

  def findById(id: E#IdType): Option[E]
}
