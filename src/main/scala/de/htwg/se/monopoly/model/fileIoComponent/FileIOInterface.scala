package de.htwg.se.monopoly.model.fileIoComponent

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Board
import de.htwg.se.monopoly.model.gameStateComponent.gameStateBaseImpl.GameState

trait FileIOInterface {

  def loadBoard(fileName: String): Board

  def loadGameState(fileName: String): GameState

  def save(board: Board, gameState: GameState, fileName: String): Unit
}
