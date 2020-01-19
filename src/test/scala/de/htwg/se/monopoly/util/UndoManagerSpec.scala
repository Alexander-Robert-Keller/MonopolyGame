package de.htwg.se.monopoly.util

import org.scalatest.{Matchers, WordSpec}

class UndoManagerSpec extends WordSpec with Matchers {

  "A UndoManager" when {
    "new" should {

      val undoManger = new UndoManager
      class TestCommand extends Command {
        override def redoStep(): Unit = null

        override def undoStep(): Unit = null
      }

      "have a Method to check if the re-/undo stack is empty" in {
        undoManger.undoStackEmpty() should be (true)
        undoManger.redoStackEmpty() should be (true)
      }

      "have a method to do a step" in {
        undoManger.doStep(new TestCommand)
        undoManger.undoStackEmpty() should be (false)
      }

      "have a method to undo a step" in {
        undoManger.undoStep()
        undoManger.undoStackEmpty() should be (true)
        undoManger.redoStackEmpty() should be (false)
      }

      "have a method to redo a step" in {
        undoManger.redoStep()
        undoManger.undoStackEmpty() should be (false)
        undoManger.redoStackEmpty() should be (true)
      }
    }
  }
}
