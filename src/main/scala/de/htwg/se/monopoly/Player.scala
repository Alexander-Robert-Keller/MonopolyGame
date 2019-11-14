package de.htwg.se.monopoly

case class Player(t_id: Int) {
  private val id: Int = t_id
  private var location: Int = 0 // the location denotes the spaces array index
  private var jailed: Boolean = false

  // Getters and setters
  def getId(): Int = {
    id
  }

  def getLocation(): Int = {
    location
  }

  def isJailed(): Boolean = {
    jailed
  }

  def setJailed(t_jailed: Boolean): Unit = {
    jailed = t_jailed
  }

  def move(): Unit = {
    if (location < 39) {
      location += 1
    } else if (location == 39) {
      location = 0
    }
  }
}
