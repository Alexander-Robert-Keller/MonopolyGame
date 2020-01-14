package de.htwg.se.monopoly

import de.htwg.se.monopoly.controller._
import de.htwg.se.monopoly.model.{Board, Dice}
import de.htwg.se.monopoly.aview.gui.GUI
import de.htwg.se.monopoly.aview.tui.TextualUserInterface

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
  val numberOfSpaces = 40
  val board: Board = new Board(numberOfPlayers, numberOfSpaces)
  var dice: Dice = Dice()

  // Runs the game
  def run(args: Array[String]): Unit = {
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
  } // end of run()
}
