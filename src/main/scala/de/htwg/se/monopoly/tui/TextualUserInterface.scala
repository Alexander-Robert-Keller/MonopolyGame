package de.htwg.se.monopoly.tui

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.controller.Controller

import scala.io.StdIn
/**
 * TODO Come up with a suitable observer pattern for the textual user interface
 */
class TextualUserInterface(controller: Controller) {

  def runMainMenuPrompt(): Boolean = {
    println(controller.mainMenu)
    val input = StdIn.readLine()
    processInputLineMainMenu(input)
  }

  def processInputLineMainMenu(input: String): Boolean = {
    input match {
      case "1" => //start game
        println("Start Game:")
        println(controller.StringGameBoard())
        true
      case "2" => //exit
        println(controller.exitGameMessage)
        false
    }
  }

  def runGameMenuPrompt(): Boolean = {
    // changed -> ok or revert?
    println(controller.gameMenu)
    val input = StdIn.readLine()
    processInputLineGameMenu(input)
  }


  def processInputLineGameMenu(input: String): Boolean = {
    input match {
      case "1" =>
        println(controller.rollDie())
        println(controller.StringGameBoard())
        true //roll dice
      case "2" =>
        println(controller.exitGameMessage)
        false //exit game
    }
  }
}
