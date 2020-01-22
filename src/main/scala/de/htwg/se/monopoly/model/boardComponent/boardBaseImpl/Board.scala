package de.htwg.se.monopoly.model.boardComponent.boardBaseImpl

import de.htwg.se.monopoly.model.boardComponent.BoardInterface
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes._

import scala.collection.mutable

case class Board(newSpaceList: Vector[Space], newPlayerList: Vector[Player], totalNumberOfPlayers: Int, totalNumberOfSpaces: Int) extends BoardInterface {

  val getTotalNumberOfSpaces: Int = totalNumberOfSpaces

  val jailLocation: Int = 10

  val playerList: Vector[Player] = newPlayerList

  val spaces: Vector[Space] = newSpaceList

  def init(): Board = {
    //TODO: rework when spaces properly named
    var initSpaces = Vector[Space]()
    initSpaces = initSpaces :+ Go() //0
    initSpaces = initSpaces :+ Property("Mediterranean Avenue", 60, -1, 2)
    initSpaces = initSpaces :+ CommunityChest()
    initSpaces = initSpaces :+ Property("Baltic Avenue", 60, -1, 4)
    initSpaces = initSpaces :+ Tax()
    initSpaces = initSpaces :+ Railroad() //5
    initSpaces = initSpaces :+ Property("Oriental Avenue", 100, -1, 6)
    initSpaces = initSpaces :+ Chance()
    initSpaces = initSpaces :+ Property("Vermont Avenue", 100, -1, 6)
    initSpaces = initSpaces :+ Property("Connecticut Avenue", 120, -1, 8)
    initSpaces = initSpaces :+ Jail() //10
    initSpaces = initSpaces :+ Property("St. Charles Place", 140, -1, 10)
    initSpaces = initSpaces :+ ElectricCompany()
    initSpaces = initSpaces :+ Property("States Avenue", 140, -1, 10)
    initSpaces = initSpaces :+ Property("Virginia Avenue", 160, -1, 12)
    initSpaces = initSpaces :+ Railroad() //15
    initSpaces = initSpaces :+ Property("St. James Place", 180, -1, 14)
    initSpaces = initSpaces :+ CommunityChest()
    initSpaces = initSpaces :+ Property("Tennessee Avenue", 180, -1, 14)
    initSpaces = initSpaces :+ Property("New York Avenue", 200, -1, 16)
    initSpaces = initSpaces :+ FreeParking() //20
    initSpaces = initSpaces :+ Property("Kentucky Avenue", 220, -1, 18)
    initSpaces = initSpaces :+ Chance()
    initSpaces = initSpaces :+ Property("Indiana Avenue", 220, -1, 18)
    initSpaces = initSpaces :+ Property("Illinois Avenue", 240, -1, 20)
    initSpaces = initSpaces :+ Railroad() //25
    initSpaces = initSpaces :+ Property("Atlantic Avenue", 260, -1, 22)
    initSpaces = initSpaces :+ Property("Ventor Avenue", 260, -1, 22)
    initSpaces = initSpaces :+ WaterWorks()
    initSpaces = initSpaces :+ Property("Marvis Gardens", 280, -1, 24)
    initSpaces = initSpaces :+ GoToJail() //30
    initSpaces = initSpaces :+ Property("Pacific Avenue", 300, -1, 26)
    initSpaces = initSpaces :+ Property("North Carolina Avenue", 300, -1, 26)
    initSpaces = initSpaces :+ CommunityChest()
    initSpaces = initSpaces :+ Property("Pennsylvania Avenue", 320, -1, 28)
    initSpaces = initSpaces :+ Railroad() //35
    initSpaces = initSpaces :+ Chance()
    initSpaces = initSpaces :+ Property("Park Palace", 350, -1, 35)
    initSpaces = initSpaces :+ Tax()
    initSpaces = initSpaces :+ Property("Boardwalk", 400, -1, 40)
    Board(initSpaces, initializePlayerList(), totalNumberOfPlayers, totalNumberOfSpaces)
  }

  def initializePlayerList(): Vector[Player] = {
    var tmpPlayerList = Vector[Player]()
    for (i <- 1 to totalNumberOfPlayers) {
      tmpPlayerList = tmpPlayerList :+ Player(i, 0, jailed = false, 1500)
    }
    tmpPlayerList
  }

  def movePlayer(moveByXSpaces: Int, currentPlayerIndex: Int): Board = {
    var tmpPlayerList = Vector[Player]()
    val chosenPlayer = currentPlayerIndex + 1
    for (player <- playerList) {
      if (player.getId == chosenPlayer) {
        tmpPlayerList = tmpPlayerList :+ player.move(moveByXSpaces, totalNumberOfSpaces)
      } else {
        tmpPlayerList = tmpPlayerList :+ player
      }
    }
    Board(spaces, tmpPlayerList, totalNumberOfPlayers, totalNumberOfSpaces)
  }

  def increasePlayerMoney(playerGetsXMoney: Int, currentPlayerIndex: Int): Board = {
    var tmpPlayerList = Vector[Player]()
    val chosenPlayer = currentPlayerIndex + 1
    for (player <- playerList) {
      if (player.getId == chosenPlayer) {
        tmpPlayerList = tmpPlayerList :+ player.increaseMoney(playerGetsXMoney)
      } else {
        tmpPlayerList = tmpPlayerList :+ player
      }
    }
    Board(spaces, tmpPlayerList, totalNumberOfPlayers, totalNumberOfSpaces)
  }

  def decreasePlayerMoney(playerPaysXMoney: Int, currentPlayerIndex: Int): Board = {
    var tmpPlayerList = Vector[Player]()
    val chosenPlayer = currentPlayerIndex + 1
    for (player <- playerList) {
      if (player.getId == chosenPlayer) {
        tmpPlayerList = tmpPlayerList :+ player.decreaseMoney(playerPaysXMoney)
      } else {
        tmpPlayerList = tmpPlayerList :+ player
      }
    }
    Board(spaces, tmpPlayerList, totalNumberOfPlayers, totalNumberOfSpaces)
  }

  def setPlayerJailedOrUnJailed(currentPlayerId: Int, jailed: Boolean): Board = {
    var tmpPlayerList = Vector[Player]()
    for (player <- playerList) {
      if (player.getId == currentPlayerId) {
        tmpPlayerList = tmpPlayerList :+ player.setJailed(jailed, jailLocation)
      } else {
        tmpPlayerList = tmpPlayerList :+ player
      }
    }
    Board(spaces, tmpPlayerList, totalNumberOfPlayers, totalNumberOfSpaces)
  }

  def setPayedRent(paysRentPlayer: Player, spaceIndex: Int): Board = {
    var tmpPlayerList = Vector[Player]()
    val receivesRentPlayer = playerList(spaces(spaceIndex).asInstanceOf[Property].ownerId - 1)
    for (player <- playerList) {
      if (player.getId == paysRentPlayer.getId) {
        tmpPlayerList = tmpPlayerList :+ player.decreaseMoney(spaces(spaceIndex).asInstanceOf[Property].rent)
      } else if (player.getId == receivesRentPlayer.getId) {
        tmpPlayerList = tmpPlayerList :+ player.increaseMoney(spaces(spaceIndex).asInstanceOf[Property].rent)
      } else {
        tmpPlayerList = tmpPlayerList :+ player
      }
    }
    Board(spaces, tmpPlayerList, totalNumberOfPlayers, totalNumberOfSpaces)
  }

  def buySpace(playerIndex: Int, location: Int): Board = {
    var newSpaces = Vector[Space]()
    for (spaceId <- 0 until totalNumberOfSpaces) {
      if (spaceId == location) {
        spaces(spaceId) match {
          case property: Property => newSpaces = newSpaces:+ Property(property.name, property.price, playerIndex + 1, property.rent)
          //TODO: add Railroads
          case _ => newSpaces = newSpaces:+ spaces(spaceId)
        }
      } else {
        newSpaces = newSpaces:+ spaces(spaceId)
      }
    }
    val tmp = decreasePlayerMoney(spaces(location).asInstanceOf[Property].price, playerIndex)
    Board(newSpaces, tmp.playerList, totalNumberOfPlayers, totalNumberOfSpaces)
  }

  override def toString: String = {
    val boardString = new mutable.StringBuilder("")
    boardString ++= "field ID:\t type: \t\t\t\t player:\n"
    var i = 0
    while (i < totalNumberOfSpaces) {
      boardString ++= "%d\t\t\t %-20s".format(i, spaces(i).getClass.toString.substring(72))
      for (player <- playerList) {
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
