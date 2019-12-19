package de.htwg.se.monopoly.util

trait Subscriber {
  def update(event: Event): Unit
}

class Publisher {
  var subscribers: Vector[Subscriber] = Vector()

  def add(s: Subscriber): Unit = subscribers = subscribers :+ s

  def remove(s: Subscriber): Unit = subscribers = subscribers.filterNot(o => o == s)

  def notifyObservers(event: Event): Unit = subscribers.foreach(o => o.update(event))
}

case class Event(name: String) {
  def getEvent: String = name
}