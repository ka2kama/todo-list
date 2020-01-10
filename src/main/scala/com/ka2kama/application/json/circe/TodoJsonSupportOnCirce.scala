package com.ka2kama.application.json.circe

import com.ka2kama.application.json.{JsonDecoder, JsonEncoder, JsonSupport}
import com.ka2kama.core.{Todo, TodoId}
import io.circe.generic.auto._
import io.circe.{Decoder, Encoder}
import javax.inject.Singleton

@Singleton
class TodoJsonSupportOnCirce extends JsonSupport[Todo] {

  implicit val idEncoder: Encoder[TodoId] = TodoIdJsonEncoder.encoder
  implicit val idDecoder: Decoder[TodoId] = TodoIdJsonDecoder.decoder

  override implicit val encoder: JsonEncoder[Todo] = JsonEncoderOnCirce[Todo]
  override implicit val decoder: JsonDecoder[Todo] = JsonDecoderOnCirce[Todo]
}

object TodoIdJsonEncoder {
  implicit val encoder: Encoder[TodoId] = Encoder[Long].contramap(_.value)
}

object TodoIdJsonDecoder {
  implicit val decoder: Decoder[TodoId] = Decoder[Long].map(TodoId)
}
