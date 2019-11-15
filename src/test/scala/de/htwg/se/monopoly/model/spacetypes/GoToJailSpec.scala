package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.model.Player
import org.scalatest.{Matchers, WordSpec}

class GoToJailSpec extends WordSpec with Matchers{

  "a GoToJail spacetype" when {
    "new" should {
      val goToJail = GoToJail()
      val player = Player(0)
      "have a way to tell if the player is on the space" in {
        goToJail.isOnSpace(player) should be (false)
      }
      "have a way to add a player, if he gets onto the space" in {
        goToJail.addPlayer(player)
        goToJail.isOnSpace(player) should be (true)
      }
      "have a way to remove the player if he leaves the space" in {
        goToJail.removePlayer(player)
        goToJail.isOnSpace(player) should be (false)
      }
    }
  }
}