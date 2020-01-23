package de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Player

case class Property(name: String, price: Int, ownerId: Int, rent: Int) extends Space {
  override def action(player: Player): Player = {
    if (!(player.getId == ownerId)) {
      return player.decreaseMoney(rent)
    }
    player
  }
}
