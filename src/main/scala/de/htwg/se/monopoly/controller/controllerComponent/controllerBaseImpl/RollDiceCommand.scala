package de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.monopoly.model.diceComponent.Dice
import de.htwg.se.monopoly.model.gameStateComponent.GameState
import de.htwg.se.monopoly.util.Command

class RollDiceCommand(controller: Controller) extends Command {

  var board: Board = controller.board
  var dice: Dice = controller.dice
  var gameState: GameState = controller.stateMachine.state

  // override def doStep: Unit = null

  override def undoStep(): Unit = {
    val newBoard: Board = controller.board
    val newDice: Dice = controller.dice
    val newGameState: GameState = controller.stateMachine.state
    controller.board = board
    controller.dice = dice
    controller.stateMachine.state = gameState
    board = newBoard
    dice = newDice
    gameState = newGameState
  }

  override def redoStep(): Unit = {
    val newBoard: Board = controller.board
    val newDice: Dice = controller.dice
    val newGameState: GameState = controller.stateMachine.state
    controller.board = board
    controller.dice = dice
    controller.stateMachine.state = gameState
    board = newBoard
    dice = newDice
    gameState = newGameState
  }
}