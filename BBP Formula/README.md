# ARSW-lab-1


## Part I - Introduction to Java Threads
1. As reviewed in the readings, complete the CountThread classes, so that they define the life cycle of a thread that prints the numbers between A and B. 

2. Complete the main method of the CountMainThreads class so that:
  - Create 3 threads of type CountThread, assigning the first one the interval [0..99], the second one [99..199], and the third one [200..299]. 
  - Start all three threads with start(). 
  - Run and check the output on screen. 
  - Change the beginning with start() to run(). How does the output change? Why?
	_With the start () method, the output is not consecutive, because the threads do not have a defined execution order, instead with the run () method the threads are executed sequentially showing the intervals in the given order on the screen._
