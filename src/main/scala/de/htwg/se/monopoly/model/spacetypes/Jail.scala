package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.model.Player

import scala.collection.mutable.ArrayBuffer

case class Jail() extends Space {
  override protected val availablePlayers: ArrayBuffer[Player] = new ArrayBuffer[Player]()

  //deleted action, because no action on normal visit in case of Jail
  override protected def action(t_player: Player): Unit = {
    t_player.setJailed(true)
    printf("Player %d got jailed\n", t_player.getId)
  }
}
