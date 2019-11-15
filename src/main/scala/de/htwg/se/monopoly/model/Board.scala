package de.htwg.se.monopoly.model

import de.htwg.se.monopoly.spacetypes._

import scala.collection.mutable.ArrayBuffer

class Board(t_numberOfPlayers: Int) {
  val die = Die()
  val totalNumberOfSpaces: Int = 40
  val totalNumberOfPlayers: Int = t_numberOfPlayers
  val spaces: Array[Space] = Array.fill[Space](totalNumberOfSpaces)(Property()) // hint: most spaces are property spaces
  val players: ArrayBuffer[Player] = {
    val buffer: ArrayBuffer[Player] = new ArrayBuffer[Player]
    for (i <- 1 to totalNumberOfPlayers) {
      buffer.append(Player(i))
    }
    buffer
  }

  this.init()

  def init(): Unit = {
    // Redefine the space layout such that it matches an actual Monopoly board layout.
    // The spaces are ordered clock-wise, i.e, index 0 denotes a "go" space, index 1 a "property" space,
    // index 2 a "community chest" etc.
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


    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    players(0).move()
    println(players(0).getLocation)
    spaces(10).addPlayer(players(0))
    spaces(10).addPlayer(players(1))
    spaces(10).actions()
  }
}
