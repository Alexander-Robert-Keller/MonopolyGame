package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.model.Player

import scala.collection.mutable.ArrayBuffer

case class GoToJail() extends Space {
  override protected val availablePlayers: ArrayBuffer[Player] = new ArrayBuffer[Player]()

  override protected def action(t_player: Player): Unit = ???
}
