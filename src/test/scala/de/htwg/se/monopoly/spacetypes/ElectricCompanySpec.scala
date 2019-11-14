package de.htwg.se.monopoly.spacetypes

import de.htwg.se.monopoly.Player

import org.scalatest.{Matchers, WordSpec}

class ElectricCompanySpec extends WordSpec with Matchers{

  "a ElectricCompany spacetype" when {
    "new" should {
      val electricCompany = ElectricCompany()
      val player = Player(0)
      "have a way to tell if the player is on the space" in {
        electricCompany.isOnSpace(player) should be (false)
      }
      "have a way to add a player, if he gets onto the space" in {
        electricCompany.addPlayer(player)
        electricCompany.isOnSpace(player) should be (true)
      }
      "have a way to remove the player if he leaves the space" in {
        electricCompany.removePlayer(player)
        electricCompany.isOnSpace(player) should be (false)
      }
    }
  }
}