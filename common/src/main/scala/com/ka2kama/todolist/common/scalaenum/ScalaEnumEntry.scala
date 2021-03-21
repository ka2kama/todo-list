package com.ka2kama.todolist.common.scalaenum

import enumeratum.EnumEntry
import enumeratum.values._

/**
 * enumメンバー定義基底クラス
 */
sealed trait ScalaEnumEntryBase extends DisplayName {

  /**
   * 列挙のメンバーとして定義されたcase objectの名前
   *  ※注意！
   *   ScalaEnumおよび元となっているenumeratumでは列挙のメンバーをcase objectで定義することを前提としているため、
   *   通常のobjectで定義した場合はwithNameメソッドなど一部のメソッドの挙動が予期しないものとなる可能性があります。
   */
  lazy final val name: String = toString
}

/**
 * 値付きenumメンバー定義基底クラス
 * @tparam ValueType 値の型
 */
sealed trait ValueScalaEnumEntry[ValueType] extends ScalaEnumEntryBase {
  self: ValueEnumEntry[ValueType] =>
}

/**
 * valueを持たないプレーンな列挙型メンバー用のtrait
 */
trait ScalaEnumEntry extends EnumEntry with ScalaEnumEntryBase {

  /**
   * entryName(withNameメソッドなどで使うメンバー名)は本来override可能だが、ScalaEnumでは上書き不可とする
   */
  override lazy final val entryName: String = name
}

/** valueとしてInt値を持つ列挙型メンバー用のtrait */
trait IntScalaEnumEntry extends IntEnumEntry with ValueScalaEnumEntry[Int]

/** valueとしてLong値を持つ列挙型メンバー用のtrait */
trait LongScalaEnumEntry extends LongEnumEntry with ValueScalaEnumEntry[Long]

/** valueとしてShort値を持つ列挙型メンバー用のtrait */
trait ShortScalaEnumEntry extends ShortEnumEntry with ValueScalaEnumEntry[Short]

/** valueとしてString値を持つ列挙型メンバー用のtrait */
trait StringScalaEnumEntry extends StringEnumEntry with ValueScalaEnumEntry[String]

/** valueとしてByte値を持つ列挙型メンバー用のtrait */
trait ByteScalaEnumEntry extends ByteEnumEntry with ValueScalaEnumEntry[Byte]

/** valueとしてChar値を持つ列挙型メンバー用のtrait */
trait CharScalaEnumEntry extends CharEnumEntry with ValueScalaEnumEntry[Char]

object ScalaEnumEntryBase {
  implicit class ScalaEnumEntryOps[A <: ScalaEnumEntryBase](val self: A) extends AnyVal {

    /**
     * @param ev 自身がメンバーとして所属している列挙を表すobject
     * @return 列挙定数の序数
     */
    def ordinal[E <: ScalaEnumBase[A]](implicit ev: E): Int = ev.indexOf(self)
  }
}
