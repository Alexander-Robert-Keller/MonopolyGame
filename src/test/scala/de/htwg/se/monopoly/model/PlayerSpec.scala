package de.htwg.se.monopoly.model.gameComponent

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.Player
import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when {
    "new" should {

      val player = Player(1, 0, jailed = false, 0)

      "have a playerId, location, money value and jailed status" in {
        player.id should be(1)
        player.money should be(0)
        player.location should be(0)
        player.jailed should be(false)
      }

      "have getters for the above mentioned values" in {
        player.getMoney should be(0)
        player.getLocation should be(0)
        player.getId should be(1)
        player.isJailed should be(false)
      }

      "have methods that create a new Player Object and change the money value, jailed status and location in contrast to the original object" in {
        player.increaseMoney(100).getMoney should be(100)
        player.decreaseMoney(100).getMoney should be(-100)
        player.setJailed(jail = true, 0).isJailed should be(true)
        player.move(2, 40).getLocation should be(2)
      }

      "have a nice String representation" in {
        player.toString should be("Player 1")
      }
    }
  }
}