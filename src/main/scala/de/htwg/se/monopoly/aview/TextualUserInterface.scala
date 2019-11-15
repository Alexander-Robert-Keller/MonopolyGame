package de.htwg.se.monopoly.aview

import de.htwg.se.monopoly.controller.Controller
import de.htwg.se.monopoly.util.{Observable, Observer}

import scala.io.StdIn
/**
 * TODO Come up with a suitable observer pattern for the textual user interface
 */
class TextualUserInterface(controller: Controller) extends Observer {

  //Adds Controller to Observable list
  controller.add(this)

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
      case _ =>
        println(controller.wrongCommand)
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
        true //roll dice
      case "2" =>
        println(controller.exitGameMessage)
        false //exit game
      case _ =>
        println(controller.wrongCommand)
        true
    }
  }

  override def update(): Unit = {
    println(controller.StringGameBoard())
  }
}
