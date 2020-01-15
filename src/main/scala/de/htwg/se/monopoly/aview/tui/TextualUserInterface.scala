package de.htwg.se.monopoly.aview.tui

import de.htwg.se.monopoly.controller._
import de.htwg.se.monopoly.util.{ExitCurrentGame, ExitProgram, FailedRedo, FailedUndo, Redo, RolledDice, StartGame, Undo}

import scala.swing.Reactor


class TextualUserInterface(controller: Controller) extends Reactor {

  listenTo(controller)

  var tuiMenu: TUI_Menu = MainMenu.determineMenu(controller.stateMachine.state)

  def displayMenuOptions(): Unit = {
    tuiMenu = tuiMenu.determineMenu(controller.stateMachine.state)
    println(tuiMenu.toString)
  }


  def processInputLine(input: String): Unit = {
    tuiMenu = tuiMenu.determineMenu(controller.stateMachine.state)
    try {
      val option = input.toInt
      tuiMenu.action(option - 1, controller)
    } catch {
      case _: Exception => tuiMenu.action(Integer.MAX_VALUE, controller)
    }
  }

  def processInputLine(args0: String, args1: String): Unit = {
    processInputLine(args0)
    processInputLine(args1)
  }

  reactions += {
    case event: StartGame =>
      println("Start Game:")
      println(controller.stringGameBoard())
      println(controller.nextPlayersRoundMessage())
      displayMenuOptions()
    case event: RolledDice =>
      println(controller.stringRolledDice)
      println(controller.stringGameBoard())
      println(controller.nextPlayersRoundMessage())  //place at later Stage, when buying/selling spaces is implemented
      displayMenuOptions()
    case event: ExitCurrentGame =>
      println(controller.exitCurrentGameMessage)
      displayMenuOptions()
    case event: ExitProgram => println(controller.exitProgramMessage)
    case event: Undo =>
      println("Undo Command successful!")
      println(controller.stringGameBoard())
      println(controller.nextPlayersRoundMessage())
      displayMenuOptions()
    case event: Redo =>
      println("Redo Command successful!")
      println(controller.stringGameBoard())
      println(controller.nextPlayersRoundMessage())
      displayMenuOptions()
    case event: FailedRedo =>
      println("There is nothing to Redo!")
      displayMenuOptions()
    case event: FailedUndo =>
      println("There is nothing to Undo!")
      displayMenuOptions()
    case _ =>
  }
}
