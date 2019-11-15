package de.htwg.se.monopoly

import de.htwg.se.monopoly.controller._
import de.htwg.se.monopoly.model.Board
import de.htwg.se.monopoly.tui.TextualUserInterface

/*
 * The game loop
 * for our Monopoly game implemented in Scala.
 * References:
 * - https://storage.googleapis.com/wzukusers/user-26913672/documents/5b7edb29e3e24V640Zku/Monopoly%20Quick%20Guide.pdf
 * - https://www.youtube.com/watch?v=AuWvMgYv03g
 */

object Game {
  private val numberOfPlayers = 2
  private val board: Board = new Board(numberOfPlayers)
  private var running: Boolean = true

  // Runs the game
  def run(): Unit = {
    init()

    val controller = new Controller()
    val tui: TextualUserInterface = new TextualUserInterface(controller)
    //run main menu
    setRunning(tui.runMainMenuPrompt())
    if (!isRunning) {
      return
    }
    do {  //runs game menu
      setRunning(tui.runGameMenuPrompt())
    } while (isRunning)
  } // end of run()

  // Setters and getters
  private def setRunning(t_running: Boolean): Unit = {
    running = t_running
  }

  def isRunning: Boolean = {
    running
  }

  // Initializes game
  private def init(): Unit = {

  }
}
