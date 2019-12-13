package de.htwg.se.monopoly

import de.htwg.se.monopoly.controller._
import de.htwg.se.monopoly.model.Board
import de.htwg.se.monopoly.aview.TextualUserInterface

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
  val exitValue = "2"

  // Runs the game
  def run(args: Array[String]): Unit = {
    val controller = new Controller()
    val tui: TextualUserInterface = new TextualUserInterface(controller)
    // Main Menu loop
    var input: String = ""
    if (!args.isEmpty) {
      tui.processInputLineMainMenu(args(0))
      gameLoop(tui, args(1))
    } else do {
      tui.displayMainMenuOptions()
      input = StdIn.readLine()
      tui.processInputLineMainMenu(input)
      if (isRunning) {
        gameLoop(tui, "")
      }
    } while (input != exitValue)
  } // end of run()

  //Game Loop
  def gameLoop(tui: TextualUserInterface, args: String): Unit = {
    if (args.length > 0) {
      tui.processInputLineGameMenu(args)
    } else do {
      tui.gameMenuOptions()
      val input = StdIn.readLine()
      tui.processInputLineGameMenu(input)
    } while (isRunning)
  } // on exit returns to main menu


  // Setters and getters
  def setRunning(t_running: Boolean): Unit = {
    running = t_running
  }

  def isRunning: Boolean = {
    running
  }

  // Initializes game
  def init(): Unit = {
    Game.setRunning(true)
    board.init()
  }

}
