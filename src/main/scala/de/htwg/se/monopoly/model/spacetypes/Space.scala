package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.model.Player

trait Space {
  def action(t_player: Player): Player
}
