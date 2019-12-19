package de.htwg.se.monopoly.aview

import de.htwg.se.monopoly.controller.{Controller, GameState}
import de.htwg.se.monopoly.util.{Event, Subscriber}


/**
 * TODO Come up with a suitable observer pattern for the textual user interface
 */
class TextualUserInterface(controller: Controller) extends Subscriber {

  //Adds Controller to Observable list
  controller.add(this)

  def displayMainMenuOptions(): Unit = {
    println(controller.mainMenu)
  }

  def processInputLine(input: String): Unit = {
    if (GameState.MAIN_MENU == GameState.state) {
      processInputLineMainMenu(input)
    } else {
      processInputLineGameMenu(input)
    }
  }

  def processInputLine(args0: String, args1: String): Unit = {
    processInputLineMainMenu(args0)
    processInputLineGameMenu(args1)
  }

  def processInputLineMainMenu(input: String): Unit = {
    input match {
      case "1" =>
        controller.initializeGame()
        controller.notifyObservers(Event("START_GAME"))
      case "200" => controller.notifyObservers(Event("EXIT_PROGRAM"))
      case _ => println(controller.wrongCommand)
    }
  }

  def gameMenuOptions(): Unit = {
    println(controller.gameMenu)
  }


  def processInputLineGameMenu(input: String): Unit = {
    input match {
      case "1" =>
        println(controller.rollDice())  //roll Dice
        println(controller.nextPlayersRoundMessage())  //place at later Stage, when buying/selling spaces is implemented
      case "2" =>
        GameState.setState("MAIN_MENU")
        controller.notifyObservers(Event("EXIT_CURRENT_GAME"))
      case _ =>
        println(controller.wrongCommand)
    }
  }

  override def update(event: Event): Unit = {
    event.getEvent match {
      case "START_GAME" =>
        println("Start Game:")
        println(controller.stringGameBoard())
        println(controller.nextPlayersRoundMessage())
      case "ROLLED_DICE" =>
        println(controller.stringRolledDice)
        println(controller.stringGameBoard())
      case "EXIT_CURRENT_GAME" =>
        println(controller.exitCurrentGameMessage)
        println(controller.mainMenu)
      case "EXIT_PROGRAM" => println(controller.exitProgramMessage)
      case _ =>
    }

  }
}
