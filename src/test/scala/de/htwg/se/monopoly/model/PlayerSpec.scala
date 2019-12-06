package de.htwg.se.monopoly.model

import org.scalatest.{Matchers, WordSpec}


class PlayerSpec extends WordSpec with Matchers{
  "A Player" when {
    "new" should {
      val player = Player(0, 0, jailed = false, 100)
      "have a ID" in {
        player.t_id should be (0)
      }
      "have a location" in {
        player.location should be (0)
      }
      "have a status, which tells us if the player is jailed" in {
        player.jailed should be (false)
      }
      "have a certain amount of money" in {
        player.money should be (100)
      }
      "have a getter for his ID" in {
        player.getId should be (0)
      }
      "have a getter for his location" in {
        player.getLocation should be (0)
      }
      "have a getter for his jailed status" in {
        player.isJailed should be (false)
      }
      "have a getter for his money" in {
        player.getMoney should be (100)
      }
      "have a nice String representation" in {
        player.toString should be ("Player 0")
      }
    }
  }
}
