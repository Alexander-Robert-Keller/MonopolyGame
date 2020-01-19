package de.htwg.se.monopoly.model.boardComponent.boardBaseImpl

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes._
import org.scalatest.{Matchers, WordSpec}

class BoardSpec extends WordSpec with Matchers {
  "A Board" when {
    "new" should {

      var gameBoard = Board(Vector[Space](), Vector[Player](), 2, 40)

      "have an Integer for the total number of Players" in {
        gameBoard.totalNumberOfPlayers should be(2)
      }

      "have an Integer for the total number of Spaces" in {
        gameBoard.totalNumberOfSpaces should be(40)
      }

      "have a Vector array newSpaceList, which contains the spaces on the Board" in {
        gameBoard.newSpaceList.length should be(0)
      }

      "have a Vector array spaces, which contains the spaces on the Board" in {
        gameBoard.spaces.length should be(0)
      }

      "have a Vector array newPlayerList, which contains the spaces on the Board" in {
        gameBoard.newPlayerList.length should be(0)
      }

      "have a Vector array PlayerList, which contains the spaces on the Board" in {
        gameBoard.playerList.length should be(0)
      }

      "have an Integer jailLocation" in {
        gameBoard.jailLocation should be(10)
      }

      "have a getter for totalNumberOfSpaces" in {
        gameBoard.getTotalNumberOfSpaces should be(40)
      }

      "have a method to initialize the Player Vector[]" in {
        val playerList = gameBoard.initializePlayerList()
        playerList.length should be(2)
      }

      "have a method to initialie the Board Vector[]" in {
        gameBoard = gameBoard.init()
        gameBoard.playerList.length should be(2)
        gameBoard.spaces.length should be(40)
      }

      "have a method to move a Player" in {
        gameBoard = gameBoard.movePlayer(2, 0)
        gameBoard.playerList(0).getLocation should be(2)
      }

      "have a method to increase a Players Money" in {
        gameBoard = gameBoard.increasePlayerMoney(100, 0)
        gameBoard.playerList(0).getMoney should be(1600)
      }

      "have a method to decrease a Players Money" in {
        gameBoard = gameBoard.decreasePlayerMoney(100, 0)
        gameBoard.playerList(0).getMoney should be(1500)
      }

      "have a method to jail or unjail a player" in {
        gameBoard = gameBoard.setPlayerJailedOrUnJailed(0, jailed = true)
        gameBoard.playerList(0).isJailed should be(true)
        gameBoard = gameBoard.setPlayerJailedOrUnJailed(0, jailed = false)
        gameBoard.playerList(0).isJailed should be(false)
      }

      "have a toString Method which returns the Board as a String" in {
        val test1 = gameBoard.init()
        test1.toString shouldBe a[String]
      }
    }
  }
}
