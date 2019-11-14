package de.htwg.se.monopoly.tui

import de.htwg.se.monopoly.controller.Controller
import scala.io.StdIn
/**
 * TODO Come up with a suitable observer pattern for the textual user interface
 */
class TextualUserInterface {
  val controller = new Controller

  def runMenuPrompt(): Unit = {
    println(controller.mainMenu)
    var continue = true
    val input = StdIn.readInt()
    continue = processInputLineMainMenu(input)
    if (!continue) {
      return
    }
    do {
      println(controller.gameMenu)
      val input = StdIn.readInt()
      continue = processInputLineGameMenu(input)
    } while (continue)
  }

  def processInputLineMainMenu(input: Int): Boolean = {
    input match {
      case 1 => //start game
        println("Start Game")
        true
      case 2 => //exit
        false
    }
  }

  def processInputLineGameMenu(input: Int): Boolean = {
    input match {
      case 1 =>
        println("roll:")
        true //roll dice
      case 2 =>
        false //exit game
    }
  }
}
