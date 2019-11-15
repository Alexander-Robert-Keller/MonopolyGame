package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.model.Player
import org.scalatest.{Matchers, WordSpec}

class PropertySpec extends WordSpec with Matchers{

  "a Property spacetype" when {
    "new" should {
      val property = Property()
      val player = Player(0)
      "have a way to tell if the player is on the space" in {
        property.isOnSpace(player) should be (false)
      }
      "have a way to add a player, if he gets onto the space" in {
        property.addPlayer(player)
        property.isOnSpace(player) should be (true)
      }
      "have a way to remove the player if he leaves the space" in {
        property.removePlayer(player)
        property.isOnSpace(player) should be (false)
      }
    }
  }
}