package de.htwg.se.monopoly.aview.tuiComponent

import com.google.inject.Guice
import de.htwg.se.monopoly.MonopolyModule
import de.htwg.se.monopoly.controller.controllerComponent.ControllerInterface
import org.scalatest.{Matchers, WordSpec}

class MenuItemsSpec extends WordSpec with Matchers {
  "A MenuItem trait" when {
     "implemented" should {
       val injector = Guice.createInjector(new MonopolyModule)
       val controller = injector.getInstance(classOf[ControllerInterface])
       controller.initializeGame()
       "have a ExitMainMenuItem" in {
         ExitMainMenuItem.name shouldBe a[String]
         // ExitMainMenuItem.action(controller)
         ExitMainMenuItem.toString shouldBe a[String]
       }
       "have a StartGameMenuItem" in {
         StartGameMenuItem.name shouldBe a[String]
         StartGameMenuItem.action(controller)
         StartGameMenuItem.toString shouldBe a[String]
       }
       "have a WrongCommandMenuItem" in {
         WrongCommandMenuItem.name shouldBe a[String]
         WrongCommandMenuItem.action(controller)
         WrongCommandMenuItem.toString shouldBe a[String]
       }
       "have a RollDiceMenuItem" in {
         RollDiceMenuItem.name shouldBe a[String]
         RollDiceMenuItem.action(controller)
         RollDiceMenuItem.toString shouldBe a[String]
       }
       "have a ExitGameMenuItem" in {
         ExitGameMenuItem.name shouldBe a[String]
         ExitGameMenuItem.action(controller)
         ExitGameMenuItem.toString shouldBe a[String]
       }
       "have a UndoMenuItem" in {
         UndoMenuItem.name shouldBe a[String]
         UndoMenuItem.action(controller)
         UndoMenuItem.toString shouldBe a[String]
       }
       "have a RedoMenuItem" in {
         RedoMenuItem.name shouldBe a[String]
         RedoMenuItem.action(controller)
         RedoMenuItem.toString shouldBe a[String]
       }
       "have a SaveGameMenuItem" in {
         SaveGameMenuItem.name shouldBe a[String]
         SaveGameMenuItem.action(controller)
         SaveGameMenuItem.toString shouldBe a[String]
       }
       "have a LoadGameMenuItem" in {
         LoadGameMenuItem.name shouldBe a[String]
         LoadGameMenuItem.action(controller)
         LoadGameMenuItem.toString shouldBe a[String]
       }
       "have a PlayerInfoMenuItem" in {
         PlayerInfoMenuItem.name shouldBe a[String]
         PlayerInfoMenuItem.action(controller)
         PlayerInfoMenuItem.toString shouldBe a[String]
       }
       "have a BuyPropertyMenuItem" in {
         BuyPropertyMenuItem.name shouldBe a[String]
         controller.initializeGame()
         controller.board = controller.board.movePlayer(9, 0)
         BuyPropertyMenuItem.action(controller)
         BuyPropertyMenuItem.toString shouldBe a[String]
       }
       "have a DontBuyPropertyMenuItem" in {
         DontBuyPropertyMenuItem.name shouldBe a[String]
         controller.board = controller.board.movePlayer(9, 0)
         DontBuyPropertyMenuItem.action(controller)
         DontBuyPropertyMenuItem.toString shouldBe a[String]
       }
    }
  }
}
