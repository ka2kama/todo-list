package com.ka2kama.todolist.core.domain.todo.model

import enumeratum.values.{IntEnum, IntEnumEntry}

sealed abstract class State(val value: Int) extends IntEnumEntry
object State extends IntEnum[State] {
  case object Unfinished extends State(0)
  case object Doing      extends State(1)
  case object Done       extends State(2)
  case object ParkingLot extends State(9)

  lazy final val values: IndexedSeq[State] = findValues
}
