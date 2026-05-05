package components

import components.ComponentUtil.*
import components.MainComponent.MainState
import io.github.ondeoma.scalactive.components.*
import org.scalajs.dom.*

object SubComponent extends BaseComponent {

  def apply(ms: MainState)
           (implicit parent: HTMLElement,
            am: AddMethod): NodesComponentController = {
    mkSimpleHtmlEsCC(genElement(ms))
  }

  private def genElement(ms: MainState) = {
    ComponentManager { implicit cm =>
      // language=html
      s"""<div style="background-color: mediumpurple; padding: 5px;">
         |    Sub Component: \${txtInputC(ms.name)}
         |</div>
         |""".stripMargin
    }
  }
  
}
