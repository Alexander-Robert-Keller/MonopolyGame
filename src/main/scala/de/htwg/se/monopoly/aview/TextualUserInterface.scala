package de.htwg.se.monopoly.aview

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.controller.{Controller, GameState}
import de.htwg.se.monopoly.util.Subscriber

/**
 * TODO Come up with a suitable observer pattern for the textual user interface
 */
class TextualUserInterface(controller: Controller) extends Subscriber {

  //Adds Controller to Observable list
  controller.add(this)

  def mainMenuOptions(): Unit = {
    println(controller.mainMenu)
  }

  def processInputLineMainMenu(input: String): Unit = {
    input match {
      case "1" => //start game
        println("Start Game:")
        controller.initializeGame()
        println(controller.stringGameBoard())
        println(controller.nextPlayersRoundMessage())
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
        println(controller.nextPlayersRoundMessage())  //place at later Stage, when buying/selling spaces is implemented
      case "2" =>
        println(controller.exitCurrentGame())  //exit game
      case _ =>
        println(controller.wrongCommand)
    }
  }

  override def update(): Unit = {
    println(controller.stringGameBoard())
  }
}
