package de.htwg.se.monopoly.model

import scala.util.Random

case class Die() {
  val random: Random.type = scala.util.Random
  def roll: Int = random.nextInt(6) + 1
}
