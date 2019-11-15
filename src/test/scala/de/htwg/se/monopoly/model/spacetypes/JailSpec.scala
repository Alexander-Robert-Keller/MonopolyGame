package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.model.Player
import org.scalatest.{Matchers, WordSpec}

class JailSpec extends WordSpec with Matchers{

  "a Jail spacetype" when {
    "new" should {
      val jail = Jail()
      val player = Player(0)
      "have a way to tell if the player is on the space" in {
        jail.isOnSpace(player) should be (false)
      }
      "have a way to add a player, if he gets onto the space" in {
        jail.addPlayer(player)
        jail.isOnSpace(player) should be (true)
      }
      "have a way to remove the player if he leaves the space" in {
        jail.removePlayer(player)
        jail.isOnSpace(player) should be (false)
      }
    }
  }
}