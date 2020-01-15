package de.htwg.se.monopoly.util

import de.htwg.se.monopoly.controller.Controller
import de.htwg.se.monopoly.model.{Board, Dice, GameState}

class UndoManager {
  private var undoStack: List[Command]= Nil
  private var redoStack: List[Command]= Nil

  def undoStackEmpty(): Boolean = undoStack.isEmpty

  def redoStackEmpty(): Boolean = redoStack.isEmpty

  def doStep(command: Command): Unit = {
    undoStack = command::undoStack
    redoStack = Nil
    // command.doStep()
  }
  def undoStep(): Unit = {
    undoStack match {
      case  Nil =>
      case head::stack =>
        head.undoStep()
        undoStack=stack
        redoStack= head::redoStack
    }
  }
  def redoStep(): Unit = {
    redoStack match {
      case Nil =>
      case head::stack =>
        head.redoStep()
        redoStack=stack
        undoStack=head::undoStack
    }
  }
}

trait Command {
  // def doStep(): Unit

  def redoStep(): Unit

  def undoStep(): Unit
}

class RollDiceCommand() extends Command {

  var board: Board = Controller.board
  var dice: Dice = Controller.dice
  var gameState: GameState = Controller.stateMachine.state

  // override def doStep: Unit = null

  override def undoStep(): Unit = {
    val newBoard: Board = Controller.board
    val newDice: Dice  = Controller.dice
    val newGameState: GameState = Controller.stateMachine.state
    Controller.board = board
    Controller.dice = dice
    Controller.stateMachine.state = gameState
    board = newBoard
    dice = newDice
    gameState = newGameState
  }

  override def redoStep(): Unit = {
    val newBoard: Board = Controller.board
    val newDice: Dice  = Controller.dice
    val newGameState: GameState = Controller.stateMachine.state
    Controller.board = board
    Controller.dice = dice
    Controller.stateMachine.state = gameState
    board = newBoard
    dice = newDice
    gameState = newGameState
  }
}