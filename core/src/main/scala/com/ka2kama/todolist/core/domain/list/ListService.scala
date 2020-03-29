package com.ka2kama.todolist.core.domain.list

import cats.data.OptionT
import com.ka2kama.todolist.core.domain.todo.model.TodoId
import com.ka2kama.todolist.core.domain.todo.repository.TodoRepository
import com.ka2kama.todolist.core.dto.todo.TodoDto
import com.typesafe.scalalogging.LazyLogging
import javax.inject.Inject
import monix.eval.Task

trait ListService {
  def get(id: TodoId): OptionT[Task, TodoDto]

  def list: Task[Seq[TodoDto]]
}

private[core] final class ListServiceImpl @Inject() (todoRepository: TodoRepository)
    extends ListService
    with LazyLogging {
  override def list: Task[Seq[TodoDto]] = {
    logger.info("listService: list")
    val todosF = todoRepository.findAll
    todosF map (_ map TodoDto.toDto)
  }

  override def get(id: TodoId): OptionT[Task, TodoDto] = {
    logger.info("listService: get")
    val todo = todoRepository.findById(id)
    todo map TodoDto.toDto
  }
}
