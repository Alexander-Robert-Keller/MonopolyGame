package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.model.Player

case class Jail() extends Space {
  override def action(t_player: Player): Player = ???
}
