package de.htwg.se.monopoly.util

import org.scalatest.{Matchers, WordSpec}

class PublisherSpec extends WordSpec with Matchers {
  "An Observable" should {
    val publisher = new Publisher
    val subscriber = new Subscriber {
      var updated: Boolean = false
      def isUpdated: Boolean = updated
      override def update(): Unit = {updated = true}
    }
    "add an Observer" in {
      publisher.add(subscriber)
      publisher.subscribers should contain (subscriber)
    }
    "notify an Observer" in {
      subscriber.isUpdated should be(false)
      publisher.notifyObservers()
      subscriber.isUpdated should be(true)
    }
    "remove an Observer" in {
      publisher.remove(subscriber)
      publisher.subscribers should not contain subscriber
    }
  }
}
