
package pez {

    import scala.actors._
    import scala.actors.Actor._
    import scala.util.Random

    class Pez extends Actor {
        private var candy = 20

        def act() {
            loop {
                react {
                    case "Take" => {
                        if (candy > 0) {
                            sender ! "Give"
                            candy -= 1
                            println(candy + " Pez left")
                        }
                        else {
                            sender ! "Empty"
                            println("Pez is empty!")
                        }
                    }
                }
            }
        }
    }

    class Child(name: String, pez: Actor) extends Actor {
        private var candy = 0

        def act() {
            pez ! "Take"
            loop {
                react {
                    case "Give" => {
                        println(name + " is eating pez")
                        candy += 1

                        Thread.sleep(Random.nextInt(3000))

                        pez ! "Take"
                    }
                    case "Empty" => {
                        println(name + " ate " + candy + " candy and " +
                                "is going out to play")
                        exit()
                    }
                }
            }
        }
    }

}


package runtime {

    import pez.{Pez, Child}

    object Main extends App {
        println("Pez dispenser concurrency!")

        val p = new Pez()

        val a = new Child("A", p)
        val b = new Child("B", p)
        val c = new Child("C", p)
        val d = new Child("D", p)

        p.start()
        a.start()
        b.start()
        c.start()
        d.start()
    }

}
