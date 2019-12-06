package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.model.{Dice, Player}

object JailedPlayerState extends PlayerState {

  override def determinePlayerState(currentPlayer: Player): Unit = {
    if (currentPlayer.isJailed) {
      playerState = JailedPlayerState
    } else {
      playerState = FreePlayerState
    }
  }

  override def rollDice(dice: Dice): String = {
    if (dice.gotDoublets()) {
      Game.board.setPlayerJailedOrUnJailed(GameState.currentPlayer, jailed = false)
      determinePlayerState(Game.board.players(GameState.currentPlayer))
      return "You got doublets, you are now a free man!\n" + playerState.rollDice(dice)
    }
    "You rolled %d and %d. ".format(dice.die1, dice.die2)  + "You are still jailed!\n"
  }
}
