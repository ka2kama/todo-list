package com.ka2kama.todolist.common.scalaenum

import enumeratum.values._

/** [[enumeratum.Enum]]および[[enumeratum.values.ValueEnum]]統一用の基底クラス。
  *
  * @tparam EntryType 列挙メンバーの型
  */
sealed trait ScalaEnumBase[EntryType <: ScalaEnumEntryBase] {

  /** 列挙されているobjectを列挙順に格納した[[IndexedSeq]]
    * [[enumeratum.Enum]]および[[enumeratum.values.ValueEnum]]のそれぞれに
    *  同名メソッドが宣言されているので、ここで統合する
    * @return 列挙されているobjectを列挙順に格納した[[IndexedSeq]]
    */
  def values: IndexedSeq[EntryType]

  /** 列挙されているobjectの数
    */
  lazy final val size: Int = values.size

  /** 列挙のメンバー名を表す文字列から対象のオブジェクトを取得する
    * @param name 列挙のメンバー名を表す文字列
    * @return 同名の列挙メンバー
    * @throws NoSuchElementException 文字列に該当する列挙メンバーが見つからなかった場合
    */
  def withName(name: String): EntryType

  /** 列挙のメンバー名を表す文字列から対象のオブジェクトを取得する
    * @param name 列挙のメンバー名を表す文字列
    * @return 同名の列挙メンバー。存在しなければNone
    */
  def withNameOption(name: String): Option[EntryType]

  /** 列挙のメンバー名を表す文字列(大文字・小文字問わず)から対象のオブジェクトを取得する
    * @param name 列挙のメンバー名を表す文字列
    * @return 同名の列挙メンバー
    * @throws NoSuchElementException 文字列に該当する列挙メンバーが見つからなかった場合
    */
  def withNameInsensitive(name: String): EntryType

  /** 列挙のメンバー名を表す文字列(大文字・小文字問わず)から対象のオブジェクトを取得する
    * @param name 列挙のメンバー名を表す文字列
    * @return 同名の列挙メンバー。存在しなければNone
    */
  def withNameInsensitiveOption(name: String): Option[EntryType]

  /** 列挙のメンバー名を表す文字列(大文字のみの文字列)から対象のオブジェクトを取得する
    * 同名でも引数の文字列に小文字が使われている場合は一致しない
    * @param name 列挙のメンバー名を表す文字列
    * @return 同名の列挙メンバー
    * @throws NoSuchElementException 文字列に該当する列挙メンバーが見つからなかった場合
    */
  def withNameUppercaseOnly(name: String): EntryType

  /** 列挙のメンバー名を表す文字列(大文字のみの文字列)から対象のオブジェクトを取得する
    * 同名でも引数の文字列に小文字が使われている場合は一致しない
    * @param name 列挙のメンバー名を表す文字列
    * @return 同名の列挙メンバー。存在しなければNone
    */
  def withNameUppercaseOnlyOption(name: String): Option[EntryType]

  /** 列挙のメンバー名を表す文字列(小文字のみの文字列)から対象のオブジェクトを取得する
    * 同名でも引数の文字列に大文字が使われている場合は一致しない
    * @param name 列挙のメンバー名を表す文字列
    * @return 同名の列挙メンバー
    * @throws NoSuchElementException 文字列に該当する列挙メンバーが見つからなかった場合
    */
  def withNameLowercaseOnly(name: String): EntryType

  /** 列挙のメンバー名を表す文字列(小文字のみの文字列)から対象のオブジェクトを取得する
    * 同名でも引数の文字列に大文字が使われている場合は一致しない
    * @param name 列挙のメンバー名を表す文字列
    * @return 同名の列挙メンバー。存在しなければNone
    */
  def withNameLowercaseOnlyOption(name: String): Option[EntryType]

  /** 引数の列挙メンバーの列挙位置を取得する
    * @param member 列挙メンバー
    * @return 引数の列挙メンバーの列挙位置。見つからなければ-1
    */
  def indexOf(member: EntryType): Int

  /** ScalaEnumEntryの拡張メソッドで自身の属するenumオブジェクトにアクセスするための暗黙の値
    */
  implicit final val implicitValueForScalaEnumEntryOps: ScalaEnumBase[EntryType] = this
}

/** [[enumeratum.values.ValueEnum]]統一用の基底クラス。各メンバーはvalueという名前の特定の値を持つ
  * [[enumeratum.Enum]]で定義されているメソッド群をこちらでも同じ実装で定義することで、利用側が同名メソッドを扱えるようにする
  * @tparam ValueType valueの型
  * @tparam EntryType 列挙メンバーの型
  */
sealed trait ValueScalaEnum[ValueType, EntryType <: ValueScalaEnumEntry[
  ValueType
] with ValueEnumEntry[ValueType]]
    extends ScalaEnumBase[EntryType] {
  self: ValueEnum[ValueType, EntryType] =>

  protected lazy final val valuesToIndex: Map[EntryType, Int] = values.zipWithIndex.toMap
  protected lazy final val namesToValuesMap: Map[String, EntryType] =
    values.map(v => v.name -> v).toMap
  protected lazy final val lowerCaseNamesToValuesMap: Map[String, EntryType] =
    namesToValuesMap.map { case (k, v) => k.toLowerCase -> v }
  protected lazy final val upperCaseNameValuesToMap: Map[String, EntryType] =
    namesToValuesMap.map { case (k, v) => k.toUpperCase -> v }

  final def withName(name: String): EntryType =
    withNameOption(name).getOrElse(throw new NoSuchElementException(buildNameNotFoundMessage(name)))
  final def withNameOption(name: String): Option[EntryType] = namesToValuesMap.get(name)

  final def withNameInsensitive(name: String): EntryType =
    withNameInsensitiveOption(name).getOrElse(
      throw new NoSuchElementException(buildNameNotFoundMessage(name))
    )
  final def withNameInsensitiveOption(name: String): Option[EntryType] =
    lowerCaseNamesToValuesMap.get(name.toLowerCase)

  final def withNameUppercaseOnly(name: String): EntryType =
    withNameUppercaseOnlyOption(name).getOrElse(
      throw new NoSuchElementException(buildNameNotFoundMessage(name))
    )
  final def withNameUppercaseOnlyOption(name: String): Option[EntryType] =
    upperCaseNameValuesToMap.get(name)

  final def withNameLowercaseOnly(name: String): EntryType =
    withNameLowercaseOnlyOption(name).getOrElse(
      throw new NoSuchElementException(buildNameNotFoundMessage(name))
    )
  final def withNameLowercaseOnlyOption(name: String): Option[EntryType] =
    lowerCaseNamesToValuesMap.get(name)

  final def indexOf(member: EntryType): Int = valuesToIndex.getOrElse(member, -1)

  private def buildNameNotFoundMessage(notFoundName: String): String =
    s"$notFoundName is not a member of Enum ($existingEntryNamesString)"

  private[this] lazy val existingEntryNamesString = values.map(_.name).mkString(", ")
}

/** valueを持たないプレーンな列挙型用のtrait
  *  [[enumeratum.Enum]]の各種メソッドのfinal化も同時に行う
  * @tparam A 列挙メンバーの型
  */
trait ScalaEnum[A <: ScalaEnumEntry] extends enumeratum.Enum[A] with ScalaEnumBase[A] {

  override final def withName(name: String): A               = super.withName(name)
  override final def withNameOption(name: String): Option[A] = super.withNameOption(name)
  override final def withNameInsensitive(name: String): A    = super.withNameInsensitive(name)
  override final def withNameUppercaseOnly(name: String): A  = super.withNameUppercaseOnly(name)
  override final def withNameLowercaseOnly(name: String): A  = super.withNameLowercaseOnly(name)
  override final def withNameInsensitiveOption(name: String): Option[A] =
    super.withNameInsensitiveOption(name)
  override final def withNameUppercaseOnlyOption(name: String): Option[A] =
    super.withNameUppercaseOnlyOption(name)
  override final def withNameLowercaseOnlyOption(name: String): Option[A] =
    super.withNameLowercaseOnlyOption(name)
  override final def indexOf(member: A): Int = super.indexOf(member)
}

/** valueとしてInt値を持つ列挙型用のtrait */
trait IntScalaEnum[A <: IntScalaEnumEntry] extends IntEnum[A] with ValueScalaEnum[Int, A]

/** valueとしてInt値を持つ列挙型用のtrait */
trait LongScalaEnum[A <: LongScalaEnumEntry] extends LongEnum[A] with ValueScalaEnum[Long, A]

/** valueとしてLong値を持つ列挙型用のtrait */
trait ShortScalaEnum[A <: ShortScalaEnumEntry] extends ShortEnum[A] with ValueScalaEnum[Short, A]

/** valueとしてString値を持つ列挙型用のtrait */
trait StringScalaEnum[A <: StringScalaEnumEntry]
    extends StringEnum[A]
    with ValueScalaEnum[String, A]

/** valueとしてByte値を持つ列挙型用のtrait */
trait ByteScalaEnum[A <: ByteScalaEnumEntry] extends ByteEnum[A] with ValueScalaEnum[Byte, A]

/** valueとしてChar値を持つ列挙型用のtrait */
trait CharScalaEnum[A <: CharScalaEnumEntry] extends CharEnum[A] with ValueScalaEnum[Char, A]
