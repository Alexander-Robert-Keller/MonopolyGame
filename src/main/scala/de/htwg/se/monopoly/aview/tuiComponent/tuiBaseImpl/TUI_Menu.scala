package de.htwg.se.monopoly.aview.tuiComponent.tuiBaseImpl

import de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.monopoly.model.gameStateComponent.GameState

import scala.collection.mutable

trait TUI_Menu extends {
  var menuOptions: Vector[MenuItems] = Vector()

  def add(s: MenuItems): Unit = menuOptions = menuOptions :+ s

  def determineMenu(gameState: GameState): TUI_Menu

  def action(index: Int, controller: Controller): Unit = {
    if (menuOptions.size > index) {
      menuOptions(index).action(controller)
    } else {
      WrongCommandMenuItem.action(controller)
    }
  }

  override def toString: String = {
    val menuString = new mutable.StringBuilder(this.getClass.toString.substring(36).replace('$', ' ') + "Options:\n")
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

  override def determineMenu(gameState: GameState): TUI_Menu = {
    if (gameState.state == gameState.MAIN_MENU) {
      MainMenu
    } else if (gameState.state == gameState.ROLL_DICE) {
      RollDiceMenu
    } else if (gameState.state == gameState.BUY_OR_UPGRADE_PROPERTY) {
      BuyOrUpgradeMenu
    } else {
      throw new IllegalArgumentException("there is no such state")
    }
  }

}

object BuyOrUpgradeMenu extends TUI_Menu {

  //TODO: Implement

  override def determineMenu(gameState: GameState): TUI_Menu = {
    if (gameState.state == gameState.MAIN_MENU) {
      MainMenu
    } else if (gameState.state == gameState.ROLL_DICE) {
      RollDiceMenu
    } else if (gameState.state == gameState.BUY_OR_UPGRADE_PROPERTY) {
      BuyOrUpgradeMenu
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

  override def determineMenu(gameState: GameState): TUI_Menu = {
    if (gameState.state == gameState.MAIN_MENU) {
      MainMenu
    } else if (gameState.state == gameState.ROLL_DICE) {
      RollDiceMenu
    } else if (gameState.state == gameState.BUY_OR_UPGRADE_PROPERTY) {
      BuyOrUpgradeMenu
    } else {
      throw new IllegalArgumentException("there is no such state")
    }
  }
}