import com.ka2kama.core.todo.TodoServiceModule
import net.codingwell.scalaguice.ScalaModule
import play.api.{Configuration, Environment}

/**
  * Sets up custom components for Play.
  *
  * https://www.playframework.com/documentation/latest/ScalaDependencyInjection
  */
class Module(environment: Environment, configuration: Configuration)
    extends ScalaModule {

  override def configure(): Unit = {
    install(new TodoServiceModule)
  }
}
