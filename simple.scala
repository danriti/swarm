
package simple {
    import scala.actors._
    import scala.actors.Actor._

    class Job(name: String) extends Actor {
        def act() {
            for(i <- 1 to 1000000000) {
                null
            }
            println(name + " -> Done")
            exit()
        }
    }

}


package runtime {

    import simple.Job

    object Main extends App {
        println("Simple concurrency!")

        val a = new Job("A")
        val b = new Job("B")
        val c = new Job("C")
        val d = new Job("D")

        a.start()
        b.start()
        c.start()
        d.start()

        println("Go!")
    }

}
