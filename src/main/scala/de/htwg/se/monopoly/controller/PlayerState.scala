package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.model.{Dice, Player}

trait PlayerState {

  def determinePlayerState(currentPlayer: Player): PlayerState

  def rollDice(dice: Dice, currentPlayerIndex: Int): Unit

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

  override def rollDice(dice: Dice, currentPlayerIndex: Int): Unit = {
      Controller.board = Controller.board.movePlayer(dice.getFaceValue, currentPlayerIndex)
  }

  override def stringRollDice(dice: Dice, currentPlayerIndex: Int): String = {
    if (Controller.getCurrentDice.hasDoublets) {
      if (Controller.board.playerList(currentPlayerIndex).getLocation - dice.getFaceValue < 0 && Controller.board.playerList(currentPlayerIndex).getLocation == 0) {
        return dice.toString + "You got doublets roll again!\n" + "You landed on Go: here are 400$\n"
      }
      if (Controller.board.playerList(currentPlayerIndex).getLocation - dice.getFaceValue < 0) {
        return dice.toString + "You got doublets roll again!\n" + "You went over Go: here are 200$\n"
      }
      dice.toString + "You got doublets roll again!\n"
    } else {
      if (Controller.board.playerList(currentPlayerIndex).getLocation - dice.getFaceValue < 0 && Controller.board.playerList(currentPlayerIndex).getLocation == 0) {
        return dice.toString + "You went over Go: here are 400$\n"
      }
      if (Controller.board.playerList(currentPlayerIndex).getLocation - dice.getFaceValue < 0) {
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

  override def rollDice(dice: Dice, currentPlayerIndex: Int): Unit = {
    if (dice.hasDoublets) {
      Controller.board = Controller.board.setPlayerJailedOrUnJailed(currentPlayerIndex, jailed = false)
      Controller.board = Controller.board.movePlayer(dice.getFaceValue, currentPlayerIndex)
    }
  }

  override def stringRollDice(dice: Dice, currentPlayerIndex: Int): String = {
    if (dice.hasDoublets) {
      "You got doublets, you are now a free man!\n" + FreePlayerState.stringRollDice(dice, currentPlayerIndex)
    } else {
      "You rolled %d and %d. ".format(dice.die1, dice.die2)  + "You are still jailed!\n"
    }
  }
}