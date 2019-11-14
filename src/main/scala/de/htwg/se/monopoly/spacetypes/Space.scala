package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.Player

import scala.collection.mutable.ArrayBuffer

trait Space {
  protected val availablePlayers: ArrayBuffer[Player]

  def addPlayer(t_player: Player): Unit = {
    availablePlayers.append(t_player)
  }

  def removePlayer(t_player: Player): Unit = {
    availablePlayers -= t_player
  }

  def isOnSpace(t_player: Player): Boolean = availablePlayers.contains(t_player)

//dont think that this is needed if we have action
/*  def actions(): Unit = {
    for (player <- availablePlayers) {
      action(player)
    }
  }*/

  def action(t_player: Player): Unit
}
