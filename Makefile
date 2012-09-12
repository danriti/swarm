all: swarm.scala
	scalac swarm.scala
	scala -cp . HelloWorld

.PHONY: clean
clean:
	rm *.class
