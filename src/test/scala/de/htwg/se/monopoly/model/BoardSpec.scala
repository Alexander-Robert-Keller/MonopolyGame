package de.htwg.se.monopoly.model

import de.htwg.se.monopoly.model.spacetypes._
import org.scalatest.{Matchers, WordSpec}

class BoardSpec extends WordSpec with Matchers{
  "A Board" when {
    "new" should {
      val board = new Board(2)
      "have a variable which contains a Dice Object" in {
        board.dice should be (Dice)
      }
      "have a total number of Players" in {
        board.totalNumberOfPlayers should be (2)
      }
      "have a total Number of Spaces" in {
        board.totalNumberOfSpaces should be (40)
      }
      "have an array with the total number of spaces and be filled with property spaces" in {
        board.spaces should be (Array.fill[Space](40)(Property()))
      }
      "have a list of all the players in the game" in {
        board.players(0).getId should be (1)
        board.players(1).getId should be (2)
      }
      "have a method which returns the current dice" in {
        board.getDice should be (board.dice)
      }
      "have a method to roll a new dice and replace the current dice variable" in {
        val dice1 = board.getDice
        board.rollDice()
        val dice2 = board.getDice
        dice1 should not be theSameInstanceAs (dice2)
      }
      "have a way to create a new player object if he moves his position" in {
        val oldValue= board.players(0).getLocation
        board.movePlayer(1, 0)
        (oldValue + 1) should be (board.players(0).getLocation)
      }
      "have a way to create a new player object in order to update the money a player has" in {
        val oldValue = board.players(0).getMoney
        board.updatePlayerMoney(100, 0)
        (oldValue + 1) should be (board.players(0).getMoney)
      }
      "have a way to create a new palyer object in order to update a players jailed status" in {
        val oldValue = board.players(0).isJailed
        board.setPlayerJailedOrUnJailed(0, jailed = true)
        !oldValue should be (board.players(0).isJailed)
        board.setPlayerJailedOrUnJailed(0, jailed = false)
      }
      "have a method that initializes the board" in {
        board.init()
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
      "have a way to return the board as String" in {
        val test = board.toString
        board.toString should be (test)
      }
    }
  }
}
