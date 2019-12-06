package de.htwg.se.monopoly.model

import scala.util.Random

case class Dice() {

  val random: Random = scala.util.Random

  val die1: Int = random.nextInt(6) + 1

  val die2: Int = random.nextInt(6) + 1

  def getEyes: Int = {die1 + die2}

  def gotDoublets(): Boolean = {die1 == die2}

  override def toString: String = "You rolled: %d and %d. Move %d spaces!\n".format(die1, die2, die1 + die2)
}
