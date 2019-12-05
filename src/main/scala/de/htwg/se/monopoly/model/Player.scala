package de.htwg.se.monopoly.model

case class Player(t_id: Int, location: Int, jailed: Boolean, money: Int) {

  def getId: Int = t_id

  def getLocation: Int = location

  def isJailed: Boolean = jailed

  def getMoney: Int = money

  override def toString: String = "Player " + getId
}
