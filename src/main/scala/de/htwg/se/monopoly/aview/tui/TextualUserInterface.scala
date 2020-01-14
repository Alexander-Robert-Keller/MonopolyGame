package de.htwg.se.monopoly.aview.tui

import de.htwg.se.monopoly.controller._

import scala.swing.Reactor


/**
 * TODO Come up with a suitable observer pattern for the textual user interface
 */
class TextualUserInterface extends Reactor {

  listenTo(Controller)

  var tuiMenu: TUI_Menu = MainMenu.determineMenu(Controller.gameState)

  def displayMenuOptions(): Unit = {
    tuiMenu = tuiMenu.determineMenu(Controller.gameState)
    println(tuiMenu.toString)
  }


  def processInputLine(input: String): Unit = {
    tuiMenu = tuiMenu.determineMenu(Controller.gameState)
    try {
      val option = input.toInt
      tuiMenu.action(option - 1)
    } catch {
      case _: Exception => tuiMenu.action(Integer.MAX_VALUE)
    }
  }

  def processInputLine(args0: String, args1: String): Unit = {
    processInputLine(args0)
    processInputLine(args1)
  }

  reactions += {
    case event: StartGame =>
      println("Start Game:")
      println(Controller.stringGameBoard())
      println(Controller.nextPlayersRoundMessage())
      displayMenuOptions()
    case event: RolledDice =>
      println(Controller.stringRolledDice)
      println(Controller.stringGameBoard())
      println(Controller.nextPlayersRoundMessage())  //place at later Stage, when buying/selling spaces is implemented
      displayMenuOptions()
    case event: ExitCurrentGame =>
      println(Controller.exitCurrentGameMessage)
      displayMenuOptions()
    case event: ExitProgram => println(Controller.exitProgramMessage)
    case _ =>
  }
}
