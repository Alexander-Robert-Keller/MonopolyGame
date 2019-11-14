package de.htwg.se.monopoly.tui

import de.htwg.se.monopoly.controller.Controller

/**
 * TODO Come up with a suitable observer pattern for the textual user interface
 */
class TextualUserInterface {
  val controller = new Controller

  def runMenuPrompt(): Unit = {

    println(controller.mainMenu)
    scala.io.StdIn.readInt()
  }
}
