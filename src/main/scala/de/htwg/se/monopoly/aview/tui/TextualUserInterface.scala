package de.htwg.se.monopoly.aview.tui

import de.htwg.se.monopoly.controller._
import de.htwg.se.monopoly.util.{ExitCurrentGame, ExitProgram, FailedRedo, FailedUndo, Redo, RolledDice, StartGame, Undo}

import scala.swing.Reactor


class TextualUserInterface extends Reactor {

  listenTo(Controller)

  var tuiMenu: TUI_Menu = MainMenu.determineMenu(Controller.stateMachine.state)

  def displayMenuOptions(): Unit = {
    tuiMenu = tuiMenu.determineMenu(Controller.stateMachine.state)
    println(tuiMenu.toString)
  }


  def processInputLine(input: String): Unit = {
    tuiMenu = tuiMenu.determineMenu(Controller.stateMachine.state)
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
    case event: Undo =>
      println("Undo Command successful!")
      println(Controller.stringGameBoard())
      println(Controller.nextPlayersRoundMessage())
      displayMenuOptions()
    case event: Redo =>
      println("Redo Command successful!")
      println(Controller.stringGameBoard())
      println(Controller.nextPlayersRoundMessage())
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
