package de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Player
import org.scalatest.{Matchers, WordSpec}

class CommunityChestSpec extends WordSpec with Matchers {

  "A CommunityChest field" when {
    "new" should {

      val field = CommunityChest()

      "have a performable Action" in {
        field.action(Player(0, 0, jailed = false, 0))
      }
    }
  }
}
