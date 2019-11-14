package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.Player
import org.scalatest.{Matchers, WordSpec}

class FreeParkingSpec extends WordSpec with Matchers{

  "a FreeParking spacetype" when {
    "new" should {
      val freeParking = FreeParking()
      val player = Player(0)
      "have a way to tell if the player is on the space" in {
        freeParking.isOnSpace(player) should be (false)
      }
      "have a way to add a player, if he gets onto the space" in {
        freeParking.addPlayer(player)
        freeParking.isOnSpace(player) should be (true)
      }
      "have a way to remove the player if he leaves the space" in {
        freeParking.removePlayer(player)
        freeParking.isOnSpace(player) should be (false)
      }
      "have a way to trigger a specific action if needed" in {
        //not jet implemented
      }
    }
  }
}