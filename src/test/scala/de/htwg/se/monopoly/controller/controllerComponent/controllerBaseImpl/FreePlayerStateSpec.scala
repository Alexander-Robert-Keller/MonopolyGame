package de.htwg.se.monopoly.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.monopoly.model.diceComponent.Dice
import org.scalatest.{Matchers, WordSpec}

class FreePlayerStateSpec extends WordSpec with Matchers {
  "A freePlayerState" should {

    val controller = new Controller
    controller.board = controller.board.init()
    controller.board = controller.board.setPlayerJailedOrUnJailed(1, jailed = true)

    "have a method to determine the current palyerState" in {
      controller.playerState.determinePlayerState(controller.board.playerList(0)) == JailedPlayerState
      controller.playerState.determinePlayerState(controller.board.playerList(1)) == FreePlayerState
    }

    "have a method to roll the dice" in {
      controller.dice = Dice()
      var location = controller.board.playerList(0).getLocation
      controller.playerState.rollDice(controller.dice, currentPlayerIndex = 0, controller)
      controller.board.playerList(0).getLocation should be > location
      location = controller.board.playerList(0).getLocation
      controller.playerState.rollDice(controller.dice, currentPlayerIndex = 0, controller)
      controller.board.playerList(0).getLocation should be > location
    }

    "have a method to get a message" in {
      controller.board = controller.board.init()
      var dice = Dice()
      while (!dice.hasDoublets) {
        dice = Dice()
      }
      controller.dice = dice
      controller.playerState.stringRollDice(dice, 0, controller) shouldBe a[String]
      controller.board = controller.board.movePlayer(1, 0)
      controller.playerState.stringRollDice(dice, 0, controller) shouldBe a[String]
      controller.board = controller.board.movePlayer(20, 0)
      controller.playerState.stringRollDice(dice, 0, controller) shouldBe a[String]

      while (dice.hasDoublets) {
        dice = Dice()
      }
      controller.dice = dice
      controller.playerState.stringRollDice(dice, 1, controller) shouldBe a[String]
      controller.board = controller.board.movePlayer(1, 1)
      controller.playerState.stringRollDice(dice, 1, controller) shouldBe a[String]
      controller.board = controller.board.movePlayer(20, 1)
      controller.playerState.stringRollDice(dice, 1, controller) shouldBe a[String]
    }
  }
}
