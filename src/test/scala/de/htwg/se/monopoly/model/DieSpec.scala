package de.htwg.se.monopoly.model

import org.scalatest.{Matchers, WordSpec}

class DieSpec extends WordSpec with Matchers{

  "A Die" when {
    "new" should {
      val die = Die()
      "have a random Number generator" in {
        die.random shouldBe a [scala.util.Random]
      }
      val eyes = die.roll
      "have a Method that creates an Integer, which is greater then 0 but less then 7" in {
        eyes should be > 0
        eyes should be < 7
      }
    }
  }
}
