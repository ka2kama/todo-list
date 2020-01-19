package com.ka2kama.todolist.core.todo.domain.repository

import com.ka2kama.todolist.core.db.todo.dao.TodoDao
import com.ka2kama.todolist.core.support.Repository
import com.ka2kama.todolist.core.todo.domain.model.{
  Content,
  State,
  Todo,
  TodoId
}
import javax.inject.Inject

private[todo] trait TodoRepository extends Repository {
  override type E = Todo
}

private[repository] class TodoRepositoryImpl @Inject()(todoDao: TodoDao)
    extends TodoRepository {

  import TodoConverter._

  override def findAll: Seq[Todo] = todoDao.findAll.map(_.toEntity)

  override def findById(id: TodoId): Option[Todo] =
    todoDao.findById(id.value).map(_.toEntity)
}

private object TodoConverter {

  import com.ka2kama.todolist.core.db.todo.TodoDto

  implicit class ToEntity(val self: TodoDto) extends AnyVal {
    def toEntity: Todo =
      Todo(TodoId(self.id), Content(self.content), State.of(self.state).get)
  }

  implicit class ToDto(val self: Todo) extends AnyVal {
    def toDto: TodoDto =
      TodoDto(self.id.value, self.content.value, self.state.value)
  }
}