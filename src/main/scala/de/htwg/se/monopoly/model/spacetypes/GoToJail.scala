package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.controller.Controller
import de.htwg.se.monopoly.model.Player

case class GoToJail() extends Space {
  override def action(t_player: Player): Player =  {
    t_player.setJailed(jail = true, 10)
  }
}
