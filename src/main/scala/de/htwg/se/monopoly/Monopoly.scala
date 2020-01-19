package de.htwg.se.monopoly

import com.google.inject.Guice
import de.htwg.se.monopoly.aview.guiComponent.GUI
import de.htwg.se.monopoly.aview.tuiComponent.TextualUserInterface
import de.htwg.se.monopoly.controller.controllerComponent.ControllerInterface

import scala.io.StdIn

object Monopoly {
  def main(args: Array[String]): Unit = {
    val injector = Guice.createInjector(new MonopolyModule)
    val controller = injector.getInstance(classOf[ControllerInterface])
    val tui: TextualUserInterface = new TextualUserInterface(controller)
    if (args.isEmpty) {
      val gui: GUI = new GUI(controller)
    }

    tui.displayMenuOptions()

    if (!args.isEmpty) {
      tui.processInputLine(args(0), args(1))
    } else do {
      var input: String = StdIn.readLine()
      tui.processInputLine(input)
    } while (true)
  }
}
