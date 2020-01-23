package de.htwg.se.monopoly.model.diceComponent

import org.scalatest.{Matchers, WordSpec}

class DiceSpec extends WordSpec with Matchers {

  "A Dice" when {
    "new" should {

      val dice = Dice()

      "have a random Number generator" in {
        dice.random shouldBe a[scala.util.Random]
      }

      "have 2 die values that are greater then 0 but less then 7" in {
        dice.die1 should be > 0
        dice.die1 should be < 7
        dice.die2 should be > 0
        dice.die2 should be < 7
      }

      "have a method getfaceValue that returns the value of eyes1 and eyes2 combined" in {
        dice.getFaceValue should be(dice.die1 + dice.die2)
      }

      "have a method doublets that returns true if eyes1 == eyes2" in {
        dice.hasDoublets should be(dice.die2 == dice.die1)
      }

      "have a method roll Dice which returns a new Dice object" in {
        val dice2 = dice.rollDice()
        dice2 shouldBe a[Dice]
      }

      "have a method toString which contains a nice String representation for die1 and die2" in {
        val test = dice.toString
        dice.toString should be(test)
      }
    }
  }
}
