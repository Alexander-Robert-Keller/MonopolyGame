package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.model.{Dice, Player}

trait PlayerState {

  def determinePlayerState(currentPlayer: Player): PlayerState

  def rollDice(dice: Dice, currentPlayerIndex: Int, controller: Controller): Unit

  def stringRollDice(dice: Dice, currentPlayerIndex: Int, controller: Controller): String
}

object FreePlayerState extends PlayerState {
  override def determinePlayerState(currentPlayer: Player): PlayerState = {
    if (currentPlayer.isJailed) {
      JailedPlayerState
    } else {
      FreePlayerState
    }
  }

  override def rollDice(dice: Dice, currentPlayerIndex: Int, controller: Controller): Unit = {
    controller.board = controller.board.movePlayer(dice.getFaceValue, currentPlayerIndex)
  }

  override def stringRollDice(dice: Dice, currentPlayerIndex: Int, controller: Controller): String = {
    if (controller.getCurrentDice.hasDoublets) {
      if (controller.board.playerList(currentPlayerIndex).getLocation - dice.getFaceValue < 0 && controller.board.playerList(currentPlayerIndex).getLocation == 0) {
        return dice.toString + "You got doublets roll again!\n" + "You landed on Go: here are 400$\n"
      }
      if (controller.board.playerList(currentPlayerIndex).getLocation - dice.getFaceValue < 0) {
        return dice.toString + "You got doublets roll again!\n" + "You went over Go: here are 200$\n"
      }
      dice.toString + "You got doublets roll again!\n"
    } else {
      if (controller.board.playerList(currentPlayerIndex).getLocation - dice.getFaceValue < 0 && controller.board.playerList(currentPlayerIndex).getLocation == 0) {
        return dice.toString + "You went over Go: here are 400$\n"
      }
      if (controller.board.playerList(currentPlayerIndex).getLocation - dice.getFaceValue < 0) {
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

  override def rollDice(dice: Dice, currentPlayerIndex: Int, controller: Controller): Unit = {
    if (dice.hasDoublets) {
      controller.board = controller.board.setPlayerJailedOrUnJailed(currentPlayerIndex, jailed = false)
      controller.board = controller.board.movePlayer(dice.getFaceValue, currentPlayerIndex)
    }
  }

  override def stringRollDice(dice: Dice, currentPlayerIndex: Int, controller: Controller): String = {
    if (dice.hasDoublets) {
      "You got doublets, you are now a free man!\n" + FreePlayerState.stringRollDice(dice, currentPlayerIndex, controller)
    } else {
      "You rolled %d and %d. ".format(dice.die1, dice.die2)  + "You are still jailed!\n"
    }
  }
}