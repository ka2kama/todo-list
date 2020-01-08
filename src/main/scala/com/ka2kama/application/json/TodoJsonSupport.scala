package com.ka2kama.application.json

import com.ka2kama.application.json.circe.{
  JsonDecoderOnCirce,
  JsonEncoderOnCirce
}
import com.ka2kama.core.Todo

trait TodoJsonSupport {
  implicit val todoJsonEncoder: JsonEncoder[Todo]
  implicit val todoJsonDecoder: JsonDecoder[Todo]
  implicit class TodoJsonEncoderExt(val self: Todo) extends JsonEncoderExt[Todo]
}
