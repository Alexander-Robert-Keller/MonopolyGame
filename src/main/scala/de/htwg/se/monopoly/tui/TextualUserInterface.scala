package de.htwg.se.monopoly.tui

/**
 * TODO Come up with a suitable observer pattern for the textual user interface
 */
class TextualUserInterface {
  def runMenuPrompt(): Unit = {
    println("[1] Start New Game")
    println("[2] Start Exit Game")
    scala.io.StdIn.readInt()
  }
}
