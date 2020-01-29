# ARSW-lab-1

### Comands to run it

``` mvn -U package ```

Execute the main function and then interrupt with ctrl + c.
``` mvn exec:java -Dexec.mainClass="edu.eci.arsw.math.Main""```

To calculate the time, manually enter the number of threads and the range.

``` mvn exec:java -Dexec.mainClass="edu.eci.arsw.PiDigits```

## Part I - Introduction to Java Threads
1. As reviewed in the readings, complete the CountThread classes, so that they define the life cycle of a thread that prints the numbers between A and B. 

![](https://github.com/jualme/ARSW-lab-1/blob/master/BBP%20Formula/img/CountThread.png)

2. Complete the main method of the CountMainThreads class so that:
  - Create 3 threads of type CountThread, assigning the first one the interval [0..99], the second one [99..199], and the third one [200..299]. 
  - Start all three threads with start(). 
  - Run and check the output on screen. 
  
  ![](https://github.com/jualme/ARSW-lab-1/blob/master/BBP%20Formula/img/CountThreadsMain.png)
  
  - Change the beginning with start() to run(). How does the output change? Why?
  
	_With the start () method, the output is not consecutive, because the threads do not have a defined execution order, instead with the run () method the threads are executed sequentially showing the intervals in the given order on the screen._
	
	

## Part II - BBP Formula Exercise

The BBP formula (Bailey – Borwein – Plouffe formula) is an algorithm that allows you to calculate the nth digit of PI in base 16, with the particularity of not needing to calculate us n-1 previous digits. This feature makes it possible to convert the problem of calculating a massive number of PI digits (in base 16) to a shamefully parallel one. In this repository you will find the implementation, along with a set of tests.

For this exercise you want to calculate, in the shortest possible time, and in a single machine (taking advantage of the multi-core characteristics of the same) at least the first million digits of PI (in base 16).

  1. Create a Thread type class that represents the life cycle of a thread that calculates a portion of the required digits. 

  2. Have the PiDigits.getDigits() function receive as an additional parameter an N value, corresponding to the number of threads between   which the solution is to be parallelized. Have that function wait until the N threads finish solving the problem to combine the answers and then return the result. For this, review the join method of the Java concurrency API. 

  3. Adjust the JUnit tests, considering the cases of using 1, 2 or 3 threads (the last one to consider an odd number of threads!)
  
  ## Part III - Performance Evaluation

From the above, implement the following sequence of experiments to calculate the million digits (hex) of PI, taking their execution times (be sure to do them on the same machine):

   * Single thread. 

   * As many threads as processing cores (have the program determine this using the Runtime API). 

   * So many threads as double processing cores. 

   * 200 threads.

   * 500 threads 

When starting the program, run the jVisualVM monitor, and as the tests run, check and record the CPU and memory consumption in each case.

### First case

Single thread

![](https://github.com/jualme/ARSW-lab-1/blob/master/BBP%20Formula/img/1Thread.PNG)

### Second case

6 threads 

![](https://github.com/jualme/ARSW-lab-1/blob/master/BBP%20Formula/img/6Threads.PNG)

### Third case

12 threads 

![](https://github.com/jualme/ARSW-lab-1/blob/master/BBP%20Formula/img/12threads.PNG)

### Fourth case

200 threads 

![](https://github.com/jualme/ARSW-lab-1/blob/master/BBP%20Formula/img/200threads.PNG)

### Fifth case

500 threads 

![](https://github.com/jualme/ARSW-lab-1/blob/master/BBP%20Formula/img/500threads.PNG)


With the above, and with the execution times given, graph solution time vs. Number of threads. Analyze and propose hypotheses with your partner for the following questions (you can take into account what is reported by jVisualVM):

![](https://github.com/jualme/ARSW-lab-1/blob/master/BBP%20Formula/img/graph.PNG)

   1. According to Amdahls law, where S (n) is the theoretical performance improvement, P the parallel fraction of the algorithm, and n the number of threads, the greater n, the greater the improvement should be. Why is the best performance not achieved with the 500 threads? How does this performance compare when 200 are used?.
   
Amdahl's law is theoretical and it doesn't have into account the time required for the processor to change between threads, that's why the real-time for n = 500 is not better than n = 200
   
   2. How does the solution behave using as many processing threads as cores compared to the result of using twice as much?
   3. According to the above, if for this problem instead of 500 threads on a single CPU, 1 wire could be used on each of 500 hypothetical machines, would Amdahls's law be better applied? If, instead, c threads were used in 500 / c distributed machines (where c is the number of cores of said machines), would it be improved? Explain your answer.
   
   Yes, it would be better applied, separating the machines and the threads, with that we avoid the time to change between the threads.
   In the second case dividing 500/c will improve the time but not as much as using a single CPU for each one of the 500 because we need to count the time to change between the threads().

<p align="center">
  <img width="190" height="66" src="https://github.com/jualme/ARSW-lab-1/blob/master/BBP%20Formula/img/ahmdahls.png">
</p>
