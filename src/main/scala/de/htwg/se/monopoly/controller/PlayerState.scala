package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.model.{Dice, Player}

trait PlayerState {

  def determinePlayerState(currentPlayer: Player): Unit =  {
    playerState.determinePlayerState(currentPlayer)
  }

  def rollDice(dice: Dice): String

  var playerState: PlayerState = FreePlayerState
}
