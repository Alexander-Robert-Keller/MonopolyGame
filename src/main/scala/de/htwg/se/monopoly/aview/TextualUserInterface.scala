package de.htwg.se.monopoly.aview

import de.htwg.se.monopoly.controller.{Controller, ExitCurrentGame, ExitProgram, GameState, RolledDice, StartGame}

import scala.swing.Reactor


/**
 * TODO Come up with a suitable observer pattern for the textual user interface
 */
class TextualUserInterface(controller: Controller) extends Reactor {

  listenTo(controller)
  //Adds Controller to Observable list


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
        controller.publish(new StartGame)
      case "200" => controller.publish(new ExitProgram)
      case _ => println(controller.wrongCommand)
    }
  }

  def gameMenuOptions(): Unit = {
    println(controller.gameMenu)
  }


  def processInputLineGameMenu(input: String): Unit = {
    input match {
      case "1" =>
        controller.rollDice() //roll Dice
      case "2" =>
        GameState.setState("MAIN_MENU")
        controller.publish(new ExitCurrentGame)
      case _ =>
        println(controller.wrongCommand)
    }
  }

  reactions += {
    case event: StartGame =>
      println("Start Game:")
      println(controller.stringGameBoard())
      println(controller.nextPlayersRoundMessage())
    case event: RolledDice =>
      println(controller.stringRolledDice)
      println(controller.stringGameBoard())
      println(controller.nextPlayersRoundMessage())  //place at later Stage, when buying/selling spaces is implemented
      println(controller.gameMenu)
    case event: ExitCurrentGame =>
      println(controller.exitCurrentGameMessage)
      println(controller.mainMenu)
    case event: ExitProgram => println(controller.exitProgramMessage)
    case _ =>
  }
}
