package de.htwg.se.monopoly.model

case class Player(t_id: Int) {
  private var location: Int = 0 // the location denotes the spaces array index
  private var jailed: Boolean = false

  // Getters and setters
  def getId: Int = t_id

  def getLocation: Int = location

  def isJailed: Boolean = jailed

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
