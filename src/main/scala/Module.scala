import com.google.inject.AbstractModule
import com.ka2kama.application.json.JsonConverter
import com.ka2kama.application.json.circe.TodoConverterOnCirce
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
    bind[JsonConverter[Todo]].to[TodoConverterOnCirce].in[Singleton]
  }
}
