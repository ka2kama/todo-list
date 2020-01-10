import com.google.inject.AbstractModule
import com.ka2kama.application.json.JsonSupport
import com.ka2kama.application.json.circe.TodoJsonSupportOnCirce
import com.ka2kama.core.Todo
import javax.inject._
import net.codingwell.scalaguice.ScalaModule
import play.api.{Configuration, Environment}

/**
  * Sets up custom components for Play.
  *
  * https://www.playframework.com/documentation/latest/ScalaDependencyInjection
  */
class Module(environment: Environment, configuration: Configuration)
    extends AbstractModule
    with ScalaModule {

  override def configure(): Unit = {
    bind[JsonSupport[Todo]].to[TodoJsonSupportOnCirce].in[Singleton]
  }
}
