package de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Player
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes.Property
import de.htwg.se.monopoly.model.gameStateComponent.GameState

import scala.util.control.Breaks._

class StateMachine(controller: Controller) extends {

  var state: GameState = GameState(0, 0, controller.numberOfPlayers)

  def getCurrentPlayer: Int = state.getCurrentPlayer

  def getNumberOfPlayers: Int = state.getNumberOfPlayers

  def setState(e: String): Unit = {
    e match {
      case "ROLL_DICE" => state = GameState(1, state.getCurrentPlayer, state.getNumberOfPlayers)
      case "BUY_PROPERTY" => state = GameState(2, state.getCurrentPlayer, state.getNumberOfPlayers)
      case "MAIN_MENU" => state = GameState(0, state.getCurrentPlayer, state.getNumberOfPlayers)
      case "FINISHED_GAME" => state = GameState(3, state.getCurrentPlayer, state.getNumberOfPlayers)
    }
  }

  def currentGameStateMessage: String = {
    state.getStateIndex match {
      case 0 => "Main Menu:"
      case 1 => "Roll Dice!"
      case 2 => "Buy or Upgrade your property now!"
      case 3 =>
        var winner: Player = Player(-1, -1, jailed = true, -1)
        for(player <- controller.getPlayerList) {
          if (player.getMoney >= 0) {
            winner = player
          }
        }
        "Game finished! \n" + winner.toString + " won!"
    }
  }

  def startGame(numberOfPlayers: Int): Unit = {
    setState("ROLL_DICE")
    setCurrentPlayer(0)
    setNumberOfPlayers(numberOfPlayers)
  }

  def setCurrentPlayer(playerIndex: Int): Unit = {
    state = GameState(state.getStateIndex, playerIndex, state.getNumberOfPlayers)
  }

  def setNumberOfPlayers(maxPlayers: Int): Unit = {
    state = GameState(state.getStateIndex, state.getCurrentPlayer, maxPlayers)
  }

  def nextState(): Unit = {
    state.getStateIndex match {
      case 0 => state = GameState(1, state.getCurrentPlayer, state.getNumberOfPlayers)
      case 1 =>
        if (buyProperty()) {
          state = GameState(2, state.getCurrentPlayer, state.getNumberOfPlayers)
        } else {
          nextRollDiceState()
        }
      case 2 =>
        nextRollDiceState()
      case 3 => state = GameState(3, nextPlayer(), state.getNumberOfPlayers)
    }
  }

  def nextRollDiceState(): Unit = {
    if (controller.dice.hasDoublets) {
      state = GameState(1, state.getCurrentPlayer, state.getNumberOfPlayers)
    } else {
      state = GameState(1, nextPlayer(), state.getNumberOfPlayers)
    }
  }

  def buyProperty(): Boolean = {
    val location = controller.board.playerList(getCurrentPlayer).getLocation
    val space = controller.board.spaces(location)
    space match {
      case property: Property =>
        if (property.ownerId < 0) {
          return true
        }
        false
        //TODO: add Railroads
      case _ => false
    }
  }


  def nextPlayer(): Int = {
    var currentPlayer = state.getCurrentPlayer
    breakable {
      while (true) {
        currentPlayer += 1
        if (currentPlayer >= state.getNumberOfPlayers) {
          currentPlayer = currentPlayer % state.getNumberOfPlayers
        }
        if (!(controller.getPlayerList(currentPlayer).getMoney < 0)) {
          break()
        }
      }
    }
    currentPlayer
  }
}
