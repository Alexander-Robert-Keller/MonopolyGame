package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.model.Player

trait Space {
  def action(player: Player): Player
}
