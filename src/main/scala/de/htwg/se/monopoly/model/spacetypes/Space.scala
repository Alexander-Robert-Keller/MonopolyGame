package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.model.Player

import scala.collection.mutable.ArrayBuffer

trait Space {
  def action(t_player: Player): Player
}
