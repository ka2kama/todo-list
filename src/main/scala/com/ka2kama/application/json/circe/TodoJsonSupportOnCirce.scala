package com.ka2kama.application.json.circe

import com.ka2kama.application.json.{
  JsonDecoder,
  JsonEncoder,
  JsonEncoderExt,
  TodoJsonSupport
}
import com.ka2kama.core.{Todo, TodoId}
import io.circe.generic.auto._
import io.circe.{Decoder, Encoder}

object TodoJsonSupportOnCirce extends TodoJsonSupport {
  import TodoIdJsonSupport.TodoIdJsonEncoder._
  import TodoIdJsonSupport.TodoIdJsonDecoder._

  private object TodoJsonEncoder extends JsonEncoderOnCirce[Todo]
  private object TodoJsonDecoder extends JsonDecoderOnCirce[Todo]

  override implicit val todoJsonEncoder: JsonEncoder[Todo] = TodoJsonEncoder
  override implicit val todoJsonDecoder: JsonDecoder[Todo] = TodoJsonDecoder
}

object TodoIdJsonSupport {

  implicit object TodoIdJsonEncoder extends JsonEncoderOnCirce[TodoId] {
    override implicit val encoder: Encoder[TodoId] =
      Encoder[Long].contramap(_.value)
  }
  implicit object TodoIdJsonDecoder extends JsonDecoderOnCirce[TodoId] {
    override implicit val decoder: Decoder[TodoId] =
      _.value.as[Long].map(TodoId)
  }

  implicit class TodoIdJsonEncoderExt(val self: TodoId)
      extends JsonEncoderExt[TodoId]
}
