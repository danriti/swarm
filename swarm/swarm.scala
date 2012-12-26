
package swarm {

    class Coordinate {
        private var xPoint = 0
        private var yPoint = 0

        def get_x() = xPoint
        def get_y() = yPoint

        def set_coords(x: Int, y: Int) {
            xPoint = x
            yPoint = y
        }

    }

    class Node {
        private val c = new Coordinate()

        //def get_coords = (c.get_x(), c.get_y())
    }

    class Grid {
        private val maxColumns = 40
        private val maxRows = 20

        def hi() = println("Test")

        def print_board() {
            for (i <- 1 to maxRows) {
                for (j <- 1 to maxColumns) {
                    print(".")
                }
                print("\n")
            }
        }
    }

}


package runtime {

    import swarm.Grid

    object Main extends App {
        println("Hello, world!")

        val g = new swarm.Grid()
        val n = new swarm.Node()

        g.hi()
        g.print_board()

    }

}
