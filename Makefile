all: swarm.scala
	scalac swarm.scala
	scala -cp . runtime.Main

.PHONY: clean
clean:
	rm *.class
