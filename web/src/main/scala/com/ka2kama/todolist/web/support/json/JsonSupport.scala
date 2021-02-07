package com.ka2kama.todolist.web.support.json

import com.ka2kama.todolist.core.dto.todo.TodoDto
import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json.{Writes, __}

object JsonSupport {

  implicit val todoJsFmt: Writes[TodoDto] = (
    (__ \ "id").write[Long] and
      (__ \ "content").write[String] and
      (__ \ "state").write[Int]
  )(unlift(TodoDto.unapply))

}
