package de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.monopoly.model.diceComponent.Dice
import org.scalatest.{Matchers, WordSpec}

class JailedPlayerStateSpec extends WordSpec with Matchers {
  "A JailedPlayerState" should {

    val controller = new Controller
    controller.board = controller.board.init()
    controller.board = controller.board.setPlayerJailedOrUnJailed(0, jailed = true)
    controller.playerState = controller.playerState.determinePlayerState(controller.board.playerList(0))

    "have a method to determine the current palyerState" in {
      controller.playerState.determinePlayerState(controller.board.playerList(0)) == JailedPlayerState
      controller.playerState.determinePlayerState(controller.board.playerList(1)) == FreePlayerState
    }

    "have a method to roll the dice" in {
      controller.board = controller.board.setPlayerJailedOrUnJailed(1, jailed = true)
      var dice = Dice()

      while (dice.hasDoublets) {
        dice = Dice()
      }
      controller.playerState.rollDice(dice,1, controller)
      while (!dice.hasDoublets) {
        dice = Dice()
      }
      controller.playerState.rollDice(dice, 1, controller)
      controller.board.playerList(1).isJailed should be (false)
    }

    "have a method to get a message" in {
      controller.board = controller.board.init()
      controller.board = controller.board.setPlayerJailedOrUnJailed(0, jailed = true)
      var dice = Dice()
      while (dice.hasDoublets) {
        dice = Dice()
      }
      controller.playerState.stringRollDice(dice, 0, controller) shouldBe a[String]
      while (!dice.hasDoublets) {
        dice = Dice()
      }
      controller.playerState.rollDice(dice, 0, controller)
      controller.playerState.stringRollDice(dice, 0, controller) shouldBe a[String]
    }
  }
}
