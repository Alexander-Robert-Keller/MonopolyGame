package de.htwg.se.monopoly.aview.tuiComponent

import com.google.inject.Guice
import de.htwg.se.monopoly.MonopolyModule
import de.htwg.se.monopoly.controller.controllerComponent.ControllerInterface
import org.scalatest.{Matchers, WordSpec}

import scala.swing.Publisher

class TextualUserInterfaceSpec extends WordSpec with Matchers {
  "A TextualUSerInterface" when {
    "new" should {
      val injector = Guice.createInjector(new MonopolyModule)
      val controller = injector.getInstance(classOf[ControllerInterface])
      val tui: TextualUserInterface = new TextualUserInterface(controller)
      "have a tui Menu variable" in {
        tui.tuiMenu should be (MainMenu)
      }
      "have a method displayMenuOptions" in {
        tui.displayMenuOptions()
      }
      "have a method to process the inputLine(also possible with args)" in {
        tui.processInputLine("1", "1")
        controller.exitGameMenu()
        tui.processInputLine("1")
        controller.stateMachine.state.getStateIndex should be (1)
      }
      "have reactions for Specific events" in {
        controller.initializeGame()
        controller.board = controller.board.movePlayer(9, 0)
        controller.buyProperty()
        controller.dontBuyProperty()
        controller.rollDice()
        controller.undoCommand()
        controller.undoCommand()
        controller.redoCommand()
        controller.redoCommand()
        controller.saveGame()
        controller.loadGame()
        controller.playerInfo()
        controller.board.decreasePlayerMoney(-1600, 0)
        controller.endFinishedGame()
        controller.initializeGame()
        controller.exitGameMenu()
        //controller.exitMainMenu()
      }
    }
  }
}
