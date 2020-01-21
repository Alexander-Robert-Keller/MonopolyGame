package de.htwg.se.monopoly.aview.tuiComponent

import de.htwg.se.monopoly.controller.controllerComponent.ControllerInterface
import de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.monopoly.model.gameStateComponent.GameState

import scala.collection.mutable

trait TUI_Menu extends {
  var menuOptions: Vector[MenuItems] = Vector()

  def add(s: MenuItems): Unit = menuOptions = menuOptions :+ s

  def determineMenu(gameState: GameState): TUI_Menu

  def action(index: Int, controller: ControllerInterface): Unit = {
    if (menuOptions.size > index) {
      menuOptions(index).action(controller)
    } else {
      WrongCommandMenuItem.action(controller)
    }
  }

  override def toString: String = {
    val menuString = new mutable.StringBuilder(this.getClass.toString.substring(45).replace('$', ' ') + "Options:\n")
    var index = 0
    while (index < menuOptions.size) {
      val tmp = menuOptions(index).name
      index += 1
      menuString ++= index.toString + ": " + tmp + "\n"
    }
    menuString.toString()
  }
}

object MainMenu extends TUI_Menu {

  add(StartGameMenuItem)
  add(ExitMainMenuItem)
  add(LoadGameMenuItem)

  override def determineMenu(gameState: GameState): TUI_Menu = {
    if (gameState.state == gameState.MAIN_MENU) {
      MainMenu
    } else if (gameState.state == gameState.ROLL_DICE) {
      RollDiceMenu
    } else if (gameState.state == gameState.BUY_PROPERTY) {
      BuyMenu
    } else {
      throw new IllegalArgumentException("there is no such state")
    }
  }

}

object BuyMenu extends TUI_Menu {

  add(BuyPropertyMenuItem)
  add(DontBuyPropertyMenuItem)
  add(UndoMenuItem)
  add(RedoMenuItem)
  add(PlayerInfoMenuItem)
  add(ExitGameMenuItem)

  override def determineMenu(gameState: GameState): TUI_Menu = {
    if (gameState.state == gameState.MAIN_MENU) {
      MainMenu
    } else if (gameState.state == gameState.ROLL_DICE) {
      RollDiceMenu
    } else if (gameState.state == gameState.BUY_PROPERTY) {
      BuyMenu
    } else {
      throw new IllegalArgumentException("there is no such state")
    }
  }
}

object RollDiceMenu extends TUI_Menu {

  add(RollDiceMenuItem)
  add(ExitGameMenuItem)
  add(UndoMenuItem)
  add(RedoMenuItem)
  add(SaveGameMenuItem)
  add(PlayerInfoMenuItem)

  override def determineMenu(gameState: GameState): TUI_Menu = {
    if (gameState.state == gameState.MAIN_MENU) {
      MainMenu
    } else if (gameState.state == gameState.ROLL_DICE) {
      RollDiceMenu
    } else if (gameState.state == gameState.BUY_PROPERTY) {
      BuyMenu
    } else {
      throw new IllegalArgumentException("there is no such state")
    }
  }
}