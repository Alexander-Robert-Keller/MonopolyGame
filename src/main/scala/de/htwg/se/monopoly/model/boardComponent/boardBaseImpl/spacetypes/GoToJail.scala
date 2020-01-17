package de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Player

case class GoToJail() extends Space {
  override def action(player: Player): Player = {
    player.setJailed(jail = true, 10)
  }
}
