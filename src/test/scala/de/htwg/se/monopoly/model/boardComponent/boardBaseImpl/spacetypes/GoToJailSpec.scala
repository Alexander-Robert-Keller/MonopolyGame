package de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Player
import org.scalatest.{Matchers, WordSpec}

class GoToJailSpec extends WordSpec with Matchers {

  "A GoToJail field" when {
    "new" should {

      val field = GoToJail()
      val player = Player(0, 0, jailed = false, 0)

      "have a performable Action" in {
        field.action(player).isJailed should be (true)
      }
    }
  }
}
