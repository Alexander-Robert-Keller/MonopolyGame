package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.Player
import org.scalatest.{Matchers, WordSpec}

class TaxSpec extends WordSpec with Matchers{

  "a Tax spacetype" when {
    "new" should {
      val tax = Tax()
      val player = Player(0)
      "have a way to tell if the player is on the space" in {
        tax.isOnSpace(player) should be (false)
      }
      "have a way to add a player, if he gets onto the space" in {
        tax.addPlayer(player)
        tax.isOnSpace(player) should be (true)
      }
      "have a way to remove the player if he leaves the space" in {
        tax.removePlayer(player)
        tax.isOnSpace(player) should be (false)
      }
      "have a way to trigger a specific action if needed" in {
        //not jet implemented
      }
    }
  }
}
