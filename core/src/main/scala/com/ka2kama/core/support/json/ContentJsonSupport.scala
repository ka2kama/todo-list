package com.ka2kama.core.support.json

import com.ka2kama.core.todo.domain.model.Content
import io.circe.{Decoder, Encoder}

trait ContentJsonSupport {
  implicit val contentEncoder: Encoder[Content] =
    Encoder[String].contramap(_.value)
  implicit val contentDecoder: Decoder[Content] = Decoder[String].map(Content)
}
