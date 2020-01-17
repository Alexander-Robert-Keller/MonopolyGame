package de.htwg.se.monopoly.model.gameComponent

object GameFactory {
  def getInstance(name: String): Game = {
    if (name.equals("Board"))
      return null
    null
  }
}
