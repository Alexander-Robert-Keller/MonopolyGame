package de.htwg.se.monopoly

import de.htwg.se.monopoly.spacetypes._
import org.scalatest.{Matchers, WordSpec}

class BoardSpec extends WordSpec with Matchers{
  "A Board" when {
    "new" should {
      val board = new Board(2)
      "have a total Number of Spaces" in {
        board.totalNumberOfSpaces should be (40)
      }
      "have a total number of Players" in {
        board.totalNumberOfPlayers should be (2)
      }
      "have a list of all the players in the game" in {
        board.players(0).getId() should be (1)
        board.players(1).getId() should be (2)
      }
      "have an array filled with different spaces, which is as long as the total number of spaces" in {
        board.spaces.length should be (40)
        board.spaces(0) should be (Go())
        board.spaces(1) should be (Property())
        board.spaces(2) should be (CommunityChest())
        board.spaces(3) should be (Property())
        board.spaces(4) should be (Tax())
        board.spaces(5) should be (Railroad())
        board.spaces(6) should be (Property())
        board.spaces(7) should be (Chance())
        board.spaces(8) should be (Property())
        board.spaces(9) should be (Property())
        board.spaces(10) should be (Jail())
        board.spaces(11) should be (Property())
        board.spaces(12) should be (ElectricCompany())
        board.spaces(13) should be (Property())
        board.spaces(14) should be (Property())
        board.spaces(15) should be (Railroad())
        board.spaces(16) should be (Property())
        board.spaces(17) should be (CommunityChest())
        board.spaces(18) should be (Property())
        board.spaces(19) should be (Property())
        board.spaces(20) should be (FreeParking())
        board.spaces(21) should be (Property())
        board.spaces(22) should be (Chance())
        board.spaces(23) should be (Property())
        board.spaces(24) should be (Property())
        board.spaces(25) should be (Railroad())
        board.spaces(26) should be (Property())
        board.spaces(27) should be (Property())
        board.spaces(28) should be (WaterWorks())
        board.spaces(29) should be (Property())
        board.spaces(30) should be (GoToJail())
        board.spaces(31) should be (Property())
        board.spaces(32) should be (Property())
        board.spaces(33) should be (CommunityChest())
        board.spaces(34) should be (Property())
        board.spaces(35) should be (Railroad())
        board.spaces(36) should be (Chance())
        board.spaces(37) should be (Property())
        board.spaces(38) should be (Tax())
        board.spaces(39) should be (Property())
      }
    }
  }
}
