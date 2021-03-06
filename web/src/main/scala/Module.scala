import com.ka2kama.todolist.core.CoreModule
import net.codingwell.scalaguice.ScalaModule

/**
 * Sets up custom components for Play.
 *
 * https://www.playframework.com/documentation/latest/ScalaDependencyInjection
 */
class Module extends ScalaModule {

  override def configure(): Unit = {
    install(new CoreModule)
  }
}
