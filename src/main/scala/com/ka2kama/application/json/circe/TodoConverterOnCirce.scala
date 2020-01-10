package com.ka2kama.application.json.circe

import com.ka2kama.application.json.{JsonDecoder, JsonEncoder, JsonConverter}
import com.ka2kama.core.{Todo, TodoId}
import io.circe.generic.auto._
import io.circe.{Decoder, Encoder}
import javax.inject.Singleton

@Singleton
class TodoConverterOnCirce extends JsonConverterOnCirce[Todo] {

  implicit val idEncoder: Encoder[TodoId] = TodoIdEncoder.encoder
  implicit val idDecoder: Decoder[TodoId] = TodoIdDecoder.decoder

  override implicit val encoder: JsonEncoderOnCirce[Todo] =
    DefaultCirceEncoder[Todo]
  override implicit val decoder: JsonDecoderOnCirce[Todo] =
    DefaultCirceDecoder[Todo]
}

object TodoIdEncoder {
  implicit val encoder: Encoder[TodoId] = Encoder[Long].contramap(_.value)
}

object TodoIdDecoder {
  implicit val decoder: Decoder[TodoId] = Decoder[Long].map(TodoId)
}
