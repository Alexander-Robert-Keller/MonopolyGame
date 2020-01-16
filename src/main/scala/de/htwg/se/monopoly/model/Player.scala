package de.htwg.se.monopoly.model

case class Player(t_id: Int, location: Int, jailed: Boolean, money: Int) {

  def getLocation: Int = location

  def move(spaces: Int, totalNumberOfSpaces: Int): Player = Player(t_id, (location + spaces) % totalNumberOfSpaces, jailed, money)

  def isJailed: Boolean = jailed

  def setJailed(jail: Boolean, position: Int): Player = Player(t_id, position, jailed, money)

  def getMoney: Int = money

  def increaseMoney(amount: Int): Player = Player(t_id, location, jailed, money + amount)

  def decreaseMoney(amount: Int): Player = Player(t_id, location, jailed, money - amount)

  override def toString: String = "Player " + getId

  def getId: Int = t_id
}
