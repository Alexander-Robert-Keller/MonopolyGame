package de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Player
import org.scalatest.{Matchers, WordSpec}

class ChanceSpec extends WordSpec with Matchers {

  "A chance field" when {
    "new" should {

      val field = Chance()

      "have a performable Action" in {
        field.action(Player(0, 0, jailed = false, 0))
      }
    }
  }
}
