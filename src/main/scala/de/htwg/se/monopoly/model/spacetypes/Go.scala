package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.model.Player

import scala.collection.mutable.ArrayBuffer

case class Go() extends Space {
 override def action(t_player: Player): Unit = {}
}
