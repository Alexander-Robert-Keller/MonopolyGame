package de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Player
import org.scalatest.{Matchers, WordSpec}

class PropertySpec extends WordSpec with Matchers {

  "A property field" when {
    "new" should {

      val field = Property("test", 10, -1, 2)
      val field1 = Property("test", 10, 0, 2)

      "have a performable Action" in {
        field.action(Player(0, 0, jailed = false, 0))
        field1.action(Player(0, 0, jailed = false, 0))
      }
    }
  }
}
