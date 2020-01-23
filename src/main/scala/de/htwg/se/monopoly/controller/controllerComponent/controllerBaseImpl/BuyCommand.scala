package de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.monopoly.model.gameStateComponent.GameState
import de.htwg.se.monopoly.util.Command

class BuyCommand(controller: Controller)  extends Command{

  var board: Board = controller.board
  var gameState: GameState = controller.stateMachine.state

  override def redoStep(): Unit = {
    val newBoard: Board = controller.board
    val newGameState: GameState = controller.stateMachine.state
    controller.board = board
    controller.stateMachine.state = gameState
    board = newBoard
    gameState = newGameState
  }

  override def undoStep(): Unit = {
    val newBoard: Board = controller.board
    val newGameState: GameState = controller.stateMachine.state
    controller.board = board
    controller.stateMachine.state = gameState
    board = newBoard
    gameState = newGameState
  }
}
