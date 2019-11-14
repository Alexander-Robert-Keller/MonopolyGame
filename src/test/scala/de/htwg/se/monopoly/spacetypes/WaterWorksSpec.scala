package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.Player
import org.scalatest.{Matchers, WordSpec}

class WaterWorksSpec extends WordSpec with Matchers{

  "a Water Works spacetype" when {
    "new" should {
      val waterworks = WaterWorks()
      val player = Player(0)
      "have a way to tell if the player is on the space" in {
        waterworks.isOnSpace(player) should be (false)
      }
      "have a way to add a player, if he gets onto the space" in {
        waterworks.addPlayer(player)
        waterworks.isOnSpace(player) should be (true)
      }
      "have a way to remove the player if he leaves the space" in {
        waterworks.removePlayer(player)
        waterworks.isOnSpace(player) should be (false)
      }
    }
  }
}