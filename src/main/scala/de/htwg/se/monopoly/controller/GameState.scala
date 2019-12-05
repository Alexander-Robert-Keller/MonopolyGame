package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.Game

object GameState extends Enumeration {
  var state: GameState.Value = Value
  val ROLL_DICE, MAIN_MENU, BUY_OR_UPGRADE_PROPERTY = Value
  state = MAIN_MENU

  var currentPlayer = 0


  def setCurrentPlayer(player: Int): Unit = {
    currentPlayer = player
  }

  def getCurrentPlayer: Int = currentPlayer

  var numberOfPlayer = 0

  def setNumberOfPlayer(maxPlayers: Int): Unit = {
    numberOfPlayer = maxPlayers
  }


  def nextPlayer(): Unit = {
    currentPlayer = currentPlayer + 1
    if (currentPlayer >= numberOfPlayer) {
      currentPlayer = currentPlayer % numberOfPlayer
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
        if (!Game.board.dice.gotDoublets()) { //Change back to BUY_OR_UPGRADE_PROPERTY, implement next player
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
}
