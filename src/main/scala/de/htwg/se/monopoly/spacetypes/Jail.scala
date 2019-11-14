package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.Player

import scala.collection.mutable.ArrayBuffer

case class Jail() extends Space {
  override protected val availablePlayers: ArrayBuffer[Player] = new ArrayBuffer[Player]()

  //deleted action, because no action on normal visit in case of Jail
  override def action(t_player: Player): Unit = print()
}
