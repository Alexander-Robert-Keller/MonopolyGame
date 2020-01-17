package de.htwg.se.monopoly.model.diceComponent.diceBaseImpl

import de.htwg.se.monopoly.model.diceComponent.DiceInterface

import scala.util.Random

case class Dice() extends DiceInterface {

  val random: Random = scala.util.Random

  val die1: Int = random.nextInt(6) + 1

  val die2: Int = random.nextInt(6) + 1

  def getFaceValue: Int = {
    die1 + die2
  }

  def hasDoublets: Boolean = {
    die1 == die2
  }

  def rollDice(): Dice = Dice()

  override def toString: String = "You rolled: %d and %d. Move %d spaces!\n".format(die1, die2, die1 + die2)
}
