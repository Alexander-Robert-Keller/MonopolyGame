package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.Player

import scala.collection.mutable.ArrayBuffer

case class Jail() extends Space {
  override protected val availablePlayers: ArrayBuffer[Player] = new ArrayBuffer[Player]()

  override protected def action(t_player: Player): Unit = {
    t_player.setJailed(true)
    printf("Player %d got jailed\n", t_player.getId())
  }
}
