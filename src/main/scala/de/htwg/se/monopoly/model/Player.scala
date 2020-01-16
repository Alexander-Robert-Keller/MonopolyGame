package de.htwg.se.monopoly.model

case class Player(id: Int, location: Int, jailed: Boolean, money: Int) {

  def getLocation: Int = location

  def move(spaces: Int, totalNumberOfSpaces: Int): Player = Player(id, (location + spaces) % totalNumberOfSpaces, jailed, money)

  def isJailed: Boolean = jailed

  def setJailed(jail: Boolean, position: Int): Player = Player(id, position, jailed, money)

  def getMoney: Int = money

  def increaseMoney(amount: Int): Player = Player(id, location, jailed, money + amount)

  def decreaseMoney(amount: Int): Player = Player(id, location, jailed, money - amount)

  override def toString: String = "Player " + getId

  def getId: Int = id
}
