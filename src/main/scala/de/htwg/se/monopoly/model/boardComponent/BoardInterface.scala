package de.htwg.se.monopoly.model.boardComponent

import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.{Board, Player}
import de.htwg.se.monopoly.model.boardComponent.boardBaseImpl.spacetypes.Space

trait BoardInterface {

  val getTotalNumberOfSpaces: Int
  val jailLocation: Int
  val playerList: Vector[Player]
  val spaces: Vector[Space]

  def init(): Board
  def movePlayer(moveByXSpaces: Int, currentPlayerIndex: Int): Board
  def increasePlayerMoney(playerGetsXMoney: Int, currentPlayerIndex: Int): Board
  def decreasePlayerMoney(playerPaysXMoney: Int, currentPlayerIndex: Int): Board
  def setPlayerJailedOrUnJailed(currentPlayerIndex: Int, jailed: Boolean): Board
  def replacePlayerInList(newPlayer: Player): Board
}
