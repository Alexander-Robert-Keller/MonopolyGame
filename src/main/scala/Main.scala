import de.htwg.se.monopoly.aview.gui.GUI
import de.htwg.se.monopoly.aview.tui.TextualUserInterface

import scala.io.StdIn

/*
 * The starting point of this program.
 */

object Main {
  def main(args: Array[String]): Unit = {
    val tui: TextualUserInterface = new TextualUserInterface
    val gui: GUI = new GUI

    tui.displayMenuOptions()

    // Main Menu loop
    var input: String = ""
    if (!args.isEmpty) {
      tui.processInputLine(args(0), args(1))
    } else do {
      input = StdIn.readLine()
      tui.processInputLine(input)
    } while (true)
  }
}
