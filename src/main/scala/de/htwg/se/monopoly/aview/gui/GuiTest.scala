package de.htwg.se.monopoly.aview.gui

import de.htwg.se.monopoly.controller.{Controller, GameState}

object GuiTest {
  def main(args: Array[String]): Unit = {
    val controller = new Controller()
    val x = new GUI(controller)
  }
}
