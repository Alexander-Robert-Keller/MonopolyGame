package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.model.Player

import scala.collection.mutable.ArrayBuffer

trait Space {
  protected val availablePlayers: ArrayBuffer[Player]

  def addPlayer(t_player: Player): Unit = {
    availablePlayers.append(t_player)
  }

  def getAvailablePlayer: String = {
    var playerList: String = ""
    for(player <- availablePlayers) {
      playerList = playerList + player.toString + "  "
    }
    playerList
  }

  def removePlayer(t_player: Player): Unit = {
    availablePlayers -= t_player
  }

  //maybe not needed?
  def isOnSpace(t_player: Player): Boolean = availablePlayers.contains(t_player)


  def actions(): Unit = {
    for (player <- availablePlayers) {
      action(player)
    }
  }

  protected def action(t_player: Player): Unit
}
