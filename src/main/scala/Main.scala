import de.htwg.se.monopoly.aview.gui.GUI
import de.htwg.se.monopoly.aview.tui.TextualUserInterface
import de.htwg.se.monopoly.controller.Controller

import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {
    val controller: Controller = new Controller
    val tui: TextualUserInterface = new TextualUserInterface(controller)
    val gui: GUI = new GUI(controller)

    tui.displayMenuOptions()

    if (!args.isEmpty) {
      tui.processInputLine(args(0), args(1))
    } else do {
      var input: String = StdIn.readLine()
      tui.processInputLine(input)
    } while (true)
  }
}
