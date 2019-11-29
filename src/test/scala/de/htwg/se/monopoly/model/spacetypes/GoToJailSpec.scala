package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.model.Player
import de.htwg.se.monopoly.model.spacetypes._
import org.scalatest.{Matchers, WordSpec}

class GoToJailSpec extends WordSpec with Matchers{

  "a GoToJail spacetype" when {
    "new" should {
      val goToJail = GoToJail()
      val player = Player(0)
      "have an action which jails the player" in {
        goToJail.action(player)
      }
    }
  }
}
