package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.aview.gui.GUI
import de.htwg.se.monopoly.aview.tui.TextualUserInterface
import de.htwg.se.monopoly.model.{Board, Dice}

import scala.io.StdIn

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
