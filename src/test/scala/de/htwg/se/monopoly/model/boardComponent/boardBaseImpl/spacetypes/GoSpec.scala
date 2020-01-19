package de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Player
import org.scalatest.{Matchers, WordSpec}

class GoSpec extends WordSpec with Matchers {

  "A Go field" when {
    "new" should {

      val field = Go()

      "have a performable Action" in {
        field.action(Player(0, 0, jailed = false, 0))
      }
    }
  }
}
