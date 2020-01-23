package de.htwg.se.monopoly

import org.scalatest.{Matchers, WordSpec}

class MonopolySpec extends WordSpec with Matchers {

  "The Monopoly main class" should {
    "accept text input as argument without readline loop, to test commands" in {
      Monopoly.main(Array[String]("1", "1"))
    }
  }
}
