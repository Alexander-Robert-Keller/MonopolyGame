package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.Player
import org.scalatest.{Matchers, WordSpec}

class GoSpec extends WordSpec with Matchers{

  "a Go spacetype" when {
    "new" should {
      val go = Go()
      val player = Player(0)
      "have a way to tell if the player is on the space" in {
        go.isOnSpace(player) should be (false)
      }
      "have a way to add a player, if he gets onto the space" in {
        go.addPlayer(player)
        go.isOnSpace(player) should be (true)
      }
      "have a way to remove the player if he leaves the space" in {
        go.removePlayer(player)
        go.isOnSpace(player) should be (false)
      }
      "have a way to trigger a specific action if needed" in {
        //not jet implemented
      }
    }
  }
}