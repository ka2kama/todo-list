package com.ka2kama.todolist.core.support

import cats.data.OptionT

import scala.concurrent.Future

private[core] trait Repository {
  type E <: Entity

  def findAll: Future[Seq[E]]

  def findById(id: E#IdType): OptionT[Future, E]
}
