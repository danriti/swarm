
package swarm {

  import scala.actors._
  import scala.actors.Actor._
  import scala.util.Random

  case object Ready
  case object Wall
  case object End

  class Point (var x: Int, var y: Int) {
    override def toString() = "(" + x + ", " + y + ")"
  }

  class Node(name: String, grid: Actor) extends Actor {
    var p = new Point(0, 0)
    var direction = 0

    def moveNode() {
      if (direction == 0) {
        p.x = p.x + 1
      } else {
        p.x = p.x - 1
      }
      grid ! p
    }

    def changeDirection() {
      p.y = p.y + 1
      if (direction == 0) {
        direction = 1
      } else {
        direction = 0
      }
      this ! Ready
    }

    def act() {
      this ! Ready
      loop {
        react {
          case Ready => moveNode()
          case Wall => changeDirection()
          case End => println(name + " has reached the end!"); exit()
        }
      }
    }
  }

  class Grid extends Actor {
    private val maxColumns = 5
    private val maxRows = 5

    def processMove(p : Point) {
      if ((p.x == maxColumns - 1) && (p.y == maxRows - 1)) {
        sender ! End
        //exit()
      } else if (p.x >= 0 && p.x < maxColumns) {
        //println(p.toString())
        Thread.sleep(Random.nextInt(1000))
        sender ! Ready
      } else {
        sender ! Wall
      }
    }

    def act() {
      loop {
        react {
          case p: Point => processMove(p)
          case x: Any   => println("Error: " + x)
        }
      }
    }
  }

}


package runtime {

  import swarm.Grid

    object Main extends App {
      println("Swarm the grid!")

      val g = new swarm.Grid()
      val a = new swarm.Node("A", g)
      val b = new swarm.Node("B", g)
      val c = new swarm.Node("C", g)

      g.start()
      a.start()
      b.start()
      c.start()
    }

}
