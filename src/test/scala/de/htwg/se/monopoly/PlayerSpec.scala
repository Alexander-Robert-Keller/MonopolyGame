package de.htwg.se.monopoly

import org.scalatest.{Matchers, WordSpec}


class PlayerSpec extends WordSpec with Matchers{
  "A Player" when {
    "new" should {
      val player = Player(0)
      "have a ID" in {
        player.t_id should be (0)
      }
      "have a getter for his ID" in {
        player.getId() should be (0)
      }
      "have a location which has a Int representation" in {
        player.getLocation() should be (0)
      }
      "have a variable that tells us if he is jailed" in {
        player.isJailed() should be(false)
      }
      "have a way to get jailed" in {
        player.setJailed(true)
        player.isJailed() should be (true)
      }


    }
  }
}
