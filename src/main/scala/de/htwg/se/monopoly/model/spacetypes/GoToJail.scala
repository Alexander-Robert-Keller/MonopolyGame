package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.controller.Game
import de.htwg.se.monopoly.model.Player

case class GoToJail() extends Space {
  override def action(t_player: Player): Unit = Game.board.setPlayerJailedOrUnJailed(t_player.getLocation, jailed = true)
}
