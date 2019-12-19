package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.Game

object GameState extends Enumeration {
  var state: GameState.Value = Value
  val ROLL_DICE, MAIN_MENU, BUY_OR_UPGRADE_PROPERTY = Value
  state = MAIN_MENU
  var controller: Controller = _

  def setController(newController: Controller): Unit = {controller = newController}

  var currentPlayer = 0

  def setCurrentPlayer(player: Int): Unit = {
    currentPlayer = player
  }

  def getCurrentPlayer: Int = currentPlayer

  var numberOfPlayers = 0

  def setNumberOfPlayers(maxPlayers: Int): Unit = {
    numberOfPlayers = maxPlayers
  }

  def getNumberOfPlayers: Int = numberOfPlayers

  def nextPlayer(): Unit = {
    currentPlayer = currentPlayer + 1
    if (currentPlayer >= numberOfPlayers) {
      currentPlayer = currentPlayer % numberOfPlayers
    }
  }

  def setState(e: String): Unit = {
    e match {
      case "ROLL_DICE" => state = ROLL_DICE
      case "BUY_OR_UPGRADE_PROPERTY" => state = BUY_OR_UPGRADE_PROPERTY
      case "MAIN_MENU" => state = MAIN_MENU
    }
  }

  def nextState(): Unit = {
    state match {
      case MAIN_MENU => state = ROLL_DICE
      case ROLL_DICE => state = ROLL_DICE
        if (!controller.getCurrentDice.hasDoublets) { //Change back to BUY_OR_UPGRADE_PROPERTY, implement next player
          nextPlayer()
        }
      case BUY_OR_UPGRADE_PROPERTY => state = ROLL_DICE
    }
  }

  def currentGameStateMessage: String = {
    state match {
      case ROLL_DICE => "Roll Dice!"
      case MAIN_MENU => "Main Menu:"
      case BUY_OR_UPGRADE_PROPERTY => "Buy or Upgrade your property now!"
    }
  }

  def init(): Unit = {
    nextState()
    setCurrentPlayer(0)
    setNumberOfPlayers(Game.numberOfPlayers)
  }
}
