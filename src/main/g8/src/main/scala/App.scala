import components.MainComponent
import io.github.ondeoma.scalactive.components.ComponentManager.*
import org.scalajs.dom.*
import org.scalajs.dom.document.body

object App {

  @main
  def main(): Unit = {
    MainComponent()(body, AddMethod.appendS(IdSelector("app"))).init()
  }

}
