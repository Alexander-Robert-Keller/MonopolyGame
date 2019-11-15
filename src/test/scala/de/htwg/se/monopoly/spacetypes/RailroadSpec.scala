package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.model.Player
import org.scalatest.{Matchers, WordSpec}

class RailroadSpec extends WordSpec with Matchers{

  "a Railroad spacetype" when {
    "new" should {
      val railroad = Railroad()
      val player = Player(0)
      "have a way to tell if the player is on the space" in {
        railroad.isOnSpace(player) should be (false)
      }
      "have a way to add a player, if he gets onto the space" in {
        railroad.addPlayer(player)
        railroad.isOnSpace(player) should be (true)
      }
      "have a way to remove the player if he leaves the space" in {
        railroad.removePlayer(player)
        railroad.isOnSpace(player) should be (false)
      }
    }
  }
}