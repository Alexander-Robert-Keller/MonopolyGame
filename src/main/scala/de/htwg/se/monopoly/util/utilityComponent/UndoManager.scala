package de.htwg.se.monopoly.util.utilityComponent

class UndoManager extends Utility {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def undoStackEmpty(): Boolean = undoStack.isEmpty

  def redoStackEmpty(): Boolean = redoStack.isEmpty

  def doStep(command: Command): Unit = {
    undoStack = command :: undoStack
    redoStack = Nil
    // command.doStep()
  }

  def undoStep(): Unit = {
    undoStack match {
      case Nil =>
      case head :: stack =>
        head.undoStep()
        undoStack = stack
        redoStack = head :: redoStack
    }
  }

  def redoStep(): Unit = {
    redoStack match {
      case Nil =>
      case head :: stack =>
        head.redoStep()
        redoStack = stack
        undoStack = head :: undoStack
    }
  }
}