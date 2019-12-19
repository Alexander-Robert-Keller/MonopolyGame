package de.htwg.se.monopoly

import de.htwg.se.monopoly.controller._
import de.htwg.se.monopoly.model.Board
import de.htwg.se.monopoly.aview.TextualUserInterface
import de.htwg.se.monopoly.aview.gui.GUI

import scala.io.StdIn

/*
 * The game loop
 * for our Monopoly game implemented in Scala.
 * References:
 * - https://storage.googleapis.com/wzukusers/user-26913672/documents/5b7edb29e3e24V640Zku/Monopoly%20Quick%20Guide.pdf
 * - https://www.youtube.com/watch?v=AuWvMgYv03g
 */

object Game {
  val numberOfPlayers = 2
  val board: Board = new Board(numberOfPlayers)
  var running: Boolean = false
  val exitValue = "200"

  // Runs the game
  def run(args: Array[String]): Unit = {
    val controller = new Controller()
    GameState.setController(controller)
    val tui: TextualUserInterface = new TextualUserInterface(controller)
    val gui: GUI = new GUI(controller)

    tui.displayMainMenuOptions()

    // Main Menu loop
    var input: String = ""
    if (!args.isEmpty) {
      tui.processInputLine(args(0), args(1))
    } else do {
      input = StdIn.readLine()
      tui.processInputLine(input)
    } while (input != exitValue)
  } // end of run()

  // Initializes game
  def init(): Unit = {
    board.init()
  }

}
