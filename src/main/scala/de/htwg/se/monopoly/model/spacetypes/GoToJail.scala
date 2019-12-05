package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.model.Player

case class GoToJail() extends Space {
  override def action(t_player: Player): Unit = Game.board.jailPlayer(t_player.getLocation)
}
