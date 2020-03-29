package com.ka2kama.todolist.core.support

import cats.data.OptionT
import monix.eval.Task

private[core] trait Repository {
  type E <: Entity

  def findAll: Task[Seq[E]]

  def findById(id: E#IdType): OptionT[Task, E]
}
