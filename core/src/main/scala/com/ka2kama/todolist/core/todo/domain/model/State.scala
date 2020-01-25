package com.ka2kama.todolist.core.todo.domain.model

import com.ka2kama.todolist.common.implicits.Implicits.OptionOps
import scala.util.Try

sealed abstract class State(val value: Int)

case object Unfinished extends State(0)
case object Doing      extends State(1)
case object Done       extends State(2)
case object ParkingLot extends State(9)

object State {
  private[this] val states =
    Map(0 -> Unfinished, 1 -> Doing, 2 -> Done, 9 -> ParkingLot)

  def of(state: Int): Try[State] =
    states
      .get(state)
      .toTry(
        new IllegalArgumentException(s"state $state is not a member of State")
      )
}
