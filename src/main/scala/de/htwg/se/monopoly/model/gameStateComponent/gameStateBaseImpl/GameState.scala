package de.htwg.se.monopoly.model.gameStateComponent.gameStateBaseImpl

import de.htwg.se.monopoly.model.gameStateComponent.GameStateInterface

case class GameState(stateIndex: Int, currentPlayer: Int, numberOfPlayers: Int) extends Enumeration with GameStateInterface {
  val ROLL_DICE, MAIN_MENU, BUY_OR_UPGRADE_PROPERTY = Value

  val state: Value = stateIndex match {
    case 0 => MAIN_MENU
    case 1 => ROLL_DICE
    case 2 => BUY_OR_UPGRADE_PROPERTY
  }

  def getCurrentPlayer: Int = currentPlayer

  def getNumberOfPlayers: Int = numberOfPlayers

  def getState: Value = state

  def getStateIndex: Int = stateIndex
}
