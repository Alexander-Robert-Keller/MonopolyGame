package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.model.{Board, Dice, GameState}
import de.htwg.se.monopoly.util.Command

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