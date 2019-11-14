package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.Player
import org.scalatest.{Matchers, WordSpec}

class ChanceSpec extends WordSpec with Matchers{

  "a Chance spacetype" when {
    "new" should {
      val chance = Chance()
      val player = Player(0)
      "have a way to tell if the player is on the space" in {
        chance.isOnSpace(player) should be (false)
      }
      "have a way to add a player, if he gets onto the space" in {
        chance.addPlayer(player)
        chance.isOnSpace(player) should be (true)
      }
      "have a way to remove the player if he leaves the space" in {
        chance.removePlayer(player)
        chance.isOnSpace(player) should be (false)
      }
    }
  }
}
