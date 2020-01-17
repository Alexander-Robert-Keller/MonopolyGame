package de.htwg.se.monopoly.model.gameComponent

import de.htwg.se.monopoly.model.gameComponent.spacetypes._

import de.htwg.se.monopoly.controller.controllerComponent.Controller

import scala.collection.mutable


case class Board(controller: Controller) {

  val totalNumberOfPlayers = controller.numberOfPlayers
  val totalNumberOfSpaces = controller.numberOfSpaces

  val getTotalNumberOfSpaces: Int = controller.numberOfSpaces

  val jailLocation: Int = 10

  var playerList: Vector[Player] = null

  var spaces: Vector[Space] = null

  def init(): Board = {
    //TODO: rework when spaces properly named
    var initSpaces = Vector[Space]()
    initSpaces = initSpaces :+ Go() //0
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ CommunityChest()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Tax()
    initSpaces = initSpaces :+ Railroad() //5
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Chance()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Jail() //10
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ ElectricCompany()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Railroad() //15
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ CommunityChest()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ FreeParking() //20
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Chance()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Railroad() //25
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ WaterWorks()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ GoToJail() //30
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ CommunityChest()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Railroad() //35
    initSpaces = initSpaces :+ Chance()
    initSpaces = initSpaces :+ Property()
    initSpaces = initSpaces :+ Tax()
    initSpaces = initSpaces :+ Property()
    initializePlayerList()
    spaces = initSpaces
    this
  }

  def initializePlayerList(): Board = {
    var tmpPlayerList = Vector[Player]()
    for (i <- 1 to totalNumberOfPlayers) {
      tmpPlayerList = tmpPlayerList :+ Player(i, 0, jailed = false, 1500)
    }
    playerList = tmpPlayerList
    this
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
    playerList = tmpPlayerList
    this
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
    playerList = tmpPlayerList
    this
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
    playerList = tmpPlayerList
    this
  }

  def setPlayerJailedOrUnJailed(currentPlayerIndex: Int, jailed: Boolean): Board = {
    var tmpPlayerList = Vector[Player]()
    val chosenPlayer = currentPlayerIndex + 1
    for (player <- playerList) {
      if (player.getId == chosenPlayer) {
        tmpPlayerList = tmpPlayerList :+ player.setJailed(jailed, jailLocation)
      } else {
        tmpPlayerList = tmpPlayerList :+ player
      }
    }
    playerList = tmpPlayerList
    this
  }

  override def toString: String = {
    val boardString = new mutable.StringBuilder("")
    boardString ++= "field ID:\t type: \t\t\t\t player:\n"
    var i = 0
    while (i < 40) {
      boardString ++= "%d\t\t\t %-20s".format(i, spaces(i).getClass.toString.substring(43))
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
