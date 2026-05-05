package components

import components.ComponentUtil.*
import io.github.ondeoma.scalactive.components.*
import org.scalajs.dom.*

object MainComponent extends BaseComponent {

  case class Human(name: String)

  case class MainState(name: RV[String] = RV(""),
                       humans: ReactiveList[Human] = ReactiveList(Nil),
                       html: RV[String] = RV("""<span style="color:red;">HTML</span>"""),
                      )

  def apply()
           (implicit parent: HTMLElement,
            am: AddMethod): NodesComponentController = {
    val ms = MainState()
    mkSimpleHtmlEsCC(genElement(ms))
  }

  private def genElement(ms: MainState) = {
    ComponentManager { implicit cm =>

      val humans = forC(ms.humans) { implicit (cm, human, i) =>
        // language=html
        s"""<tr>
           |  <td>$i</td>
           |  <td>${human.name}</td>
           |</tr>
           |""".stripMargin
      }

      val nonEmpty = ms.humans.mapC(_.nonEmpty)
      val isEmpty = nonEmpty.mapC(!_)

      // language=html
      s"""<div style="background-color: deepskyblue; padding: 5px;">
         |    <h1>Humans</h1>
         |    <table ${showIf(nonEmpty)}>
         |      <theasd>
         |          <th>No.</th>
         |          <th>Name</th>
         |      </theasd>
         |      <tbody>
         |        $humans
         |      </tbody>
         |    </table>
         |
         |    <div ${showIf(isEmpty)}>
         |        Empty...
         |    </div>
         |    
         |    <hr>
         |    
         |    <div>
         |        Input Human Name: ${txtC(ms.name)}
         |    </div>
         |    <div>    
         |        ${%(SubComponent(ms))}
         |    </div>
         |    
         |
         |    <button ${evClick(addHuman(ms))}>Add Human</button>
         |    
         |    <hr>
         |    <hr>
         |    
         |    <h1>Rendering HTML</h1> 
         |    ${txtInputC(ms.html)}
         |    ${htmlC(ms.html)}
         |    
         |    <hr>
         |    <hr>
         |    
         |    <div>
         |        Powered By <img src="scala-js-site-logo.svg" height="22" alt="Scala.js" style="position:relative; top:5px;" >
         |    </div>
         |</div>
         |""".stripMargin
    }
  }

  private def addHuman(ms: MainState): Unit = {
    ms.humans.add(Human(ms.name.v))
  }


}
