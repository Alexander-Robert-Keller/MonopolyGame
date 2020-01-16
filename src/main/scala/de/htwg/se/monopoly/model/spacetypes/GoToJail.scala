package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.model.Player

case class GoToJail() extends Space {
  override def action(player: Player): Player = {
    player.setJailed(jail = true, 10)
  }
}
