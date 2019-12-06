package de.htwg.se.monopoly.util

import de.htwg.se.monopoly.Game
import de.htwg.se.monopoly.controller.GameState

object InitializerFacade {
  def initializeGame(): Unit = {
    Game.init()
    GameState.init()
  }
}
