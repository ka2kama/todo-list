package com.ka2kama.todolist.core.domain.todo.repository

import cats.data.OptionT
import com.ka2kama.todolist.core.domain.todo.model.{Content, State, Todo, TodoId}
import com.ka2kama.todolist.core.support.Repository
import com.ka2kama.todolist.data.todo.dao.TodoDao
import javax.inject.Inject
import monix.eval.Task

trait TodoRepository extends Repository {
  override type E = Todo
}

private[core] final class TodoRepositoryImpl @Inject() (todoDao: TodoDao) extends TodoRepository {

  import TodoConverter._

  override def findAll: Task[Seq[Todo]] =
    for {
      todos <- todoDao.findAll
    } yield todos.map(_.toEntity)

  override def findById(id: TodoId): OptionT[Task, Todo] = {
    val dto = todoDao.findById(id.value)
    OptionT(dto).map(_.toEntity)
  }
}

private object TodoConverter {

  import com.ka2kama.todolist.data.todo.TodoDto

  implicit class ToEntity(val self: TodoDto) extends AnyVal {
    def toEntity: Todo =
      Todo(TodoId(self.id), Content(self.content), State.of(self.state).get)
  }

  implicit class ToDto(val self: Todo) extends AnyVal {
    def toDto: TodoDto =
      TodoDto(self.id.value, self.content.value, self.state.value)
  }
}
