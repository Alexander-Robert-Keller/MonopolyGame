package de.htwg.se.monopoly.aview.tuiComponent

import com.google.inject.Guice
import de.htwg.se.monopoly.MonopolyModule
import de.htwg.se.monopoly.controller.controllerComponent.ControllerInterface
import de.htwg.se.monopoly.model.gameStateComponent.GameState
import org.scalatest.{Matchers, WordSpec}

class TUI_MenuSpec extends WordSpec with Matchers {
  "A Tui Menu trait" when {
    "implemented" should {
      val injector = Guice.createInjector(new MonopolyModule)
      val controller = injector.getInstance(classOf[ControllerInterface])
      controller.initializeGame()
      "have a method toString" in {
        MainMenu.toString shouldBe a[String]
      }
      "have a Method action" in {
        MainMenu.action(0, controller)
        controller.board.spaces.size should not be 0
        MainMenu.action(10, controller)
      }
      "have a MainMenu" in {
        MainMenu.menuOptions.size should not be 0
      }
      "have for MainMenu a method determine playerState" in {
        MainMenu.determineMenu(GameState(0, 0, 2)) should equal (MainMenu)
        MainMenu.determineMenu(GameState(1, 0, 2)) should equal (RollDiceMenu)
        MainMenu.determineMenu(GameState(2, 0, 2)) should equal (BuyMenu)
        MainMenu.determineMenu(GameState(3, 0, 2)) should equal (FinishedGameMenu)
      }
      "have a BuyMenu" in {
        BuyMenu.menuOptions.size should not be 0
      }
      "have for BuyMenu a method determine playerState" in {
        BuyMenu.determineMenu(GameState(0, 0, 2)) should equal (MainMenu)
        BuyMenu.determineMenu(GameState(1, 0, 2)) should equal (RollDiceMenu)
        BuyMenu.determineMenu(GameState(2, 0, 2)) should equal (BuyMenu)
        BuyMenu.determineMenu(GameState(3, 0, 2)) should equal (FinishedGameMenu)
      }
      "have a RollDiceMenu" in {
        RollDiceMenu.menuOptions.size should not be 0
      }
      "have for RollDiceMenu a method determine playerState" in {
        RollDiceMenu.determineMenu(GameState(0, 0, 2)) should equal (MainMenu)
        RollDiceMenu.determineMenu(GameState(1, 0, 2)) should equal (RollDiceMenu)
        RollDiceMenu.determineMenu(GameState(2, 0, 2)) should equal (BuyMenu)
        RollDiceMenu.determineMenu(GameState(3, 0, 2)) should equal (FinishedGameMenu)
      }
      "have a FinishedGameMenu" in {
        FinishedGameMenu.menuOptions.size should not be 0
      }
      "have for FinishedGameMenu a method determine playerState" in {
        FinishedGameMenu.determineMenu(GameState(0, 0, 2)) should equal (MainMenu)
        FinishedGameMenu.determineMenu(GameState(1, 0, 2)) should equal (RollDiceMenu)
        FinishedGameMenu.determineMenu(GameState(2, 0, 2)) should equal (BuyMenu)
        FinishedGameMenu.determineMenu(GameState(3, 0, 2)) should equal (FinishedGameMenu)
      }
    }
  }
}
