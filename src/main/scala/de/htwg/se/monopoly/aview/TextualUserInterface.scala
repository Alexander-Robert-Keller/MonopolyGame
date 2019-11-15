package de.htwg.se.monopoly.aview

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.controller.Controller
import de.htwg.se.monopoly.util.{Observable, Observer}

import scala.io.StdIn
/**
 * TODO Come up with a suitable observer pattern for the textual user interface
 */
class TextualUserInterface(controller: Controller) extends Observer {

  //Adds Controller to Observable list
  controller.add(this)

  def mainMenuOptions(): Unit = {
    println(controller.mainMenu)
  }

  def processInputLineMainMenu(input: String): Unit = {
    input match {
      case "1" => //start game
        println("Start Game:")
        Game.init()
        Game.setRunning(true)
        println(controller.StringGameBoard())
      case "2" => //exit
        println(controller.exitProgramMessage)
      case _ =>
        println(controller.wrongCommand)
    }
  }

  def gameMenuOptions(): Unit = {
    println(controller.gameMenu)
  }


  def processInputLineGameMenu(input: String): Unit = {
    input match {
      case "1" =>
        println(controller.rollDie())  //roll Dice
      case "2" =>
        println(controller.exitCurrentGameMessage)  //exit game
        Game.setRunning(false)
        Game.currentGameState = "MainMenu"
      case _ =>
        println(controller.wrongCommand)
    }
  }

  override def update(): Unit = {
    println(controller.StringGameBoard())
  }
}
