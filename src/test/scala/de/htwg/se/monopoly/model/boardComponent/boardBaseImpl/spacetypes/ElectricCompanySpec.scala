package de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Player
import org.scalatest.{Matchers, WordSpec}

class ElectricCompanySpec extends WordSpec with Matchers {

  "A ElectricCompany field" when {
    "new" should {

      val field = ElectricCompany()

      "have a performable Action" in {
        field.action(Player(0, 0, jailed = false, 0))
      }
    }
  }
}
