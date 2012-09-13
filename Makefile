simple: simple.scala
	scalac simple.scala
	scala -cp . runtime.Main

all: swarm.scala
	scalac swarm.scala
	scala -cp . runtime.Main

.PHONY: clean
clean:
	rm *.class
