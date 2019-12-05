package de.htwg.se.monopoly.model.spacetypes

import de.htwg.se.monopoly.Game
import org.scalatest.{Matchers, WordSpec}

class GoToJailSpec extends WordSpec with Matchers{

  "a GoToJail spacetype" when {
    "new" should {
      val goToJail = GoToJail()
      val game = Game
      "have an action which jails the player" in {
        goToJail.action(game.board.players(0))
        game.board.players(0).isJailed should be (true)
        game.board.setPlayerJailedOrUnJailed(0, jailed = false)
      }
    }
  }
}
