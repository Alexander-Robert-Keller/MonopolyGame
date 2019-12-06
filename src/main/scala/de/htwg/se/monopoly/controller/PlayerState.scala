package de.htwg.se.monopoly.controller

import de.htwg.se.monopoly.model.{Dice, Player}

trait PlayerState {

  def determinePlayerState(currentPlayer: Player): PlayerState

  def rollDice(dice: Dice): String
}
