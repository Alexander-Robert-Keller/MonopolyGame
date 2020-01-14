package de.htwg.se.monopoly.model

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.controller.Controller

trait PlayerState {

  def determinePlayerState(currentPlayer: Player): PlayerState

  def rollDice(dice: Dice, currentPlayerIndex: Int): PlayerState

  def stringRollDice(dice: Dice, currentPlayerIndex: Int): String
}

object FreePlayerState extends PlayerState {
  override def determinePlayerState(currentPlayer: Player): PlayerState = {
    if (currentPlayer.isJailed) {
      JailedPlayerState
    } else {
      FreePlayerState
    }
  }

  override def rollDice(dice: Dice, currentPlayerIndex: Int): PlayerState= {
      Game.board.movePlayer(dice.getFaceValue, currentPlayerIndex)
      FreePlayerState
  }

  override def stringRollDice(dice: Dice, currentPlayerIndex: Int): String = {
    if (Controller.getCurrentDice.hasDoublets) {
      if (dice.getFaceValue + Game.board.players(currentPlayerIndex).getLocation == 0) {
        return dice.toString + "You landed on Go: here are 400$\n"
      }
      if (dice.getFaceValue + Game.board.players(currentPlayerIndex).getLocation > 0) {
        return dice.toString + "You went over Go: here are 200$\n"
      }
      dice.toString
    } else {
      if (dice.getFaceValue + Game.board.players(Controller.gameState.currentPlayer).getLocation == Game.board.getTotalNumberOfSpaces) {
        return dice.toString + "You went over Go: here are 400$\n"
      }
      if (dice.getFaceValue + Game.board.players(Controller.gameState.currentPlayer).getLocation > Game.board.getTotalNumberOfSpaces) {
        return dice.toString + "You went over Go: here are 200$\n"
      }
      dice.toString
    }
  }
}

object JailedPlayerState extends PlayerState {

  override def determinePlayerState(currentPlayer: Player): PlayerState = {
    if (currentPlayer.isJailed) {
      JailedPlayerState
    } else {
      FreePlayerState
    }
  }

  override def rollDice(dice: Dice, currentPlayerIndex: Int): PlayerState = {
    if (dice.hasDoublets) {
      Game.board.setPlayerJailedOrUnJailed(currentPlayerIndex, jailed = false)
      Game.board.movePlayer(dice.getFaceValue, currentPlayerIndex)
      return FreePlayerState
    }
    JailedPlayerState
  }

  override def stringRollDice(dice: Dice, currentPlayerIndex: Int): String = {
    if (dice.hasDoublets) {
      val freePlayerState: PlayerState = determinePlayerState(Game.board.players(currentPlayerIndex))
      freePlayerState.rollDice(dice, currentPlayerIndex)
      "You got doublets, you are now a free man!\n" + freePlayerState.stringRollDice(dice, currentPlayerIndex)
    } else {
      "You rolled %d and %d. ".format(dice.die1, dice.die2)  + "You are still jailed!\n"
    }
  }
}