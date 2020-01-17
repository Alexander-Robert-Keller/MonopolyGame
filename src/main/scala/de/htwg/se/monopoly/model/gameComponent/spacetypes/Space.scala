package de.htwg.se.monopoly.model.gameComponent.spacetypes

import de.htwg.se.monopoly.model.gameComponent.Player

trait Space {
  def action(player: Player): Player
}
