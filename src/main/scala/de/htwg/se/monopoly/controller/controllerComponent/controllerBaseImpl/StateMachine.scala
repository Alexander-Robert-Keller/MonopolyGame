package de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.monopoly.model.gameStateComponent.GameState

class StateMachine(controller: Controller) extends Enumeration {

  val ROLL_DICE, MAIN_MENU, BUY_OR_UPGRADE_PROPERTY = Value

  var state: GameState = GameState(0, 0, controller.numberOfPlayers)

  def getCurrentPlayer: Int = state.getCurrentPlayer

  def getNumberOfPlayers: Int = state.getNumberOfPlayers

  def setState(e: String): Unit = {
    e match {
      case "ROLL_DICE" => state = GameState(1, state.getCurrentPlayer, state.getNumberOfPlayers)
      case "BUY_OR_UPGRADE_PROPERTY" => state = GameState(2, state.getCurrentPlayer, state.getNumberOfPlayers)
      case "MAIN_MENU" => state = GameState(0, state.getCurrentPlayer, state.getNumberOfPlayers)
    }
  }

  def currentGameStateMessage: String = {
    state.getStateIndex match {
      case 0 => "Main Menu:"
      case 1 => "Roll Dice!"
      case 2 => "Buy or Upgrade your property now!"
    }
  }

  def startGame(numberOfPlayers: Int): Unit = {
    nextState()
    setCurrentPlayer(0)
    setNumberOfPlayers(numberOfPlayers)
  }

  def setCurrentPlayer(player: Int): Unit = {
    state = GameState(state.getStateIndex, state.getCurrentPlayer, state.getNumberOfPlayers)
  }

  def setNumberOfPlayers(maxPlayers: Int): Unit = {
    state = GameState(state.getStateIndex, state.getCurrentPlayer, maxPlayers)
  }

  def nextState(): Unit = {
    state.getStateIndex match {
      case 0 => state = GameState(1, state.getCurrentPlayer, state.getNumberOfPlayers)
      case 1 => //TODO: change when other options are implemented
        if (controller.dice.hasDoublets) {
          state = GameState(1, state.getCurrentPlayer, state.getNumberOfPlayers)
        } else {
          state = GameState(1, nextPlayer(), state.getNumberOfPlayers)
        }
      case 2 => state = GameState(1, nextPlayer(), state.getNumberOfPlayers) //TODO: maybe change when above state is changed
    }
  }

  def nextPlayer(): Int = {
    var currentPlayer = state.getCurrentPlayer + 1
    if (currentPlayer >= state.getNumberOfPlayers) {
      currentPlayer = currentPlayer % state.getNumberOfPlayers
    }
    currentPlayer
  }
}
