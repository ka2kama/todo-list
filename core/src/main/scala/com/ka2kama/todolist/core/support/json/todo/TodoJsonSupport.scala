package com.ka2kama.todolist.core.support.json.todo

trait TodoJsonSupport extends TodoJsonEncoder with TodoJsonDecoder

trait TodoJsonEncoder extends TodoIdJsonEncoder with ContentJsonEncoder with StateJsonEncoder
trait TodoJsonDecoder extends TodoIdJsonDecoder with ContentJsonDecoder with StateJsonDecoder
