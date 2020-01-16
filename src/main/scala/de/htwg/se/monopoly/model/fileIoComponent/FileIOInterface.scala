package de.htwg.se.monopoly.model.fileIoComponent

import de.htwg.se.monopoly.model.{Board, Dice, GameState}

trait FileIOInterface {

  def loadBoard(fileName: String): Board

  def loadGameState(fileName: String): GameState

  def save(board: Board, gameState: GameState, fileName: String): Unit
}
