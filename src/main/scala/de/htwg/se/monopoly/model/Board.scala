package de.htwg.se.monopoly.model

import de.htwg.se.monopoly.model.spacetypes._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Board(totalNumberOfPlayers: Int, totalNumberOfSpaces: Int) {

  val getTotalNumberOfSpaces: Int = totalNumberOfPlayers

  val jailLocation: Int = 10

  val spaces: Array[Space] = Array.fill[Space](totalNumberOfSpaces)(Property()) // hint: most spaces are property spaces

  val players: ArrayBuffer[Player] = {
    val buffer: ArrayBuffer[Player] = new ArrayBuffer[Player]
    for (i <- 1 to totalNumberOfPlayers) {
      buffer.append(Player(i, 0, jailed = false, 1500))
    }
    buffer
  }

  def movePlayer(moveByXSpaces: Int, currentPlayerID: Int): Unit = {
    players(currentPlayerID) = players(currentPlayerID).move(moveByXSpaces, totalNumberOfPlayers)
  }

  def increasePlayerMoney(playerGetsXMoney: Int , currentPlayerID: Int): Unit = {
    players(currentPlayerID) = players(currentPlayerID).increaseMoney(playerGetsXMoney)
  }

  def decreasePlayerMoney(playerPaysXMoney: Int , currentPlayerID: Int): Unit = {
    players(currentPlayerID) = players(currentPlayerID).decreaseMoney(playerPaysXMoney)
  }

  def setPlayerJailedOrUnJailed(currentPlayerID: Int, jailed: Boolean): Unit = {
    players(currentPlayerID) = players(currentPlayerID).setJailed(jailed, jailLocation)
  }

  def init(): Unit = {
    spaces(0) = Go()
    spaces(2) = CommunityChest()
    spaces(5) = Railroad()
    spaces(4) = Tax()
    spaces(7) = Chance()
    spaces(10) = Jail()
    spaces(12) = ElectricCompany()
    spaces(15) = Railroad()
    spaces(17) = CommunityChest()
    spaces(20) = FreeParking()
    spaces(25) = Railroad()
    spaces(22) = Chance()
    spaces(28) = WaterWorks()
    spaces(30) = GoToJail()
    spaces(33) = CommunityChest()
    spaces(35) = Railroad()
    spaces(36) = Chance()
    spaces(38) = Tax()
  }

  override def toString: String = {
    val boardString = new mutable.StringBuilder("")
    boardString ++= "field ID:\t type: \t\t\t\t player:\n"
    var i = 0
    while (i < 40) {
      boardString ++= "%d\t\t\t %-20s".format(i, spaces(i).getClass.toString.substring(43))
      for (player <- players) {
        if (player.getLocation == i) {
          boardString ++= "Player %d  ".format(player.getId)
        }
      }
      boardString ++= "\n"
      i = i + 1
    }
    boardString.toString()
  }
}
