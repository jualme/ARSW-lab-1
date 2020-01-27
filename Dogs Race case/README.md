# ARSW-lab-1

# Part I

Creation, commissioning and coordination of threads.

  1. Review the "concurrent cousins" program (in the folder part1), provided in the package edu.eci.arsw.primefinder. This is a program that calculates the prime numbers between two intervals, distributing their search among independent threads. For now, it has a single thread that seeks cousins ​​between 0 and 30,000,000. Run it, open the operating system process manager, and verify how many cores are used by it.

  2. Modify the program so that, instead of solving the problem with a single thread, do it with three, where each of these will make up the first part of the original problem. Check the operation again, and again check the use of the equipment cores.

  3. What you have been asked for is: you must modify the application so that when 5 seconds have elapsed since the execution started, all the threads are stopped and the number of primes ​​found so far is displayed. Then, you must wait for the user to press ENTER to resume their execution.

## Solution

> [Parte 1](parte1)

## Part II

For this exercise, you will work with a greyhound racing simulator (folder part2), whose graphic representation corresponds to the following figure:

_to do add image_

In the simulation, all the greyhounds have the same speed (at the programming level), so the winning greyhound will be the one that (for reasons of chance) has been most benefited by the scheduling of the processor (that is, the one with the most cycles CPU has been granted during the race). The application model is as follows:

_to do add image_

As you can see, greyhounds are thread objects, and their progress is displayed in the Canodromo class, which is basically a Swing form. All greyhounds (by default, there are 17 greyhounds running on a 100-meter track) share access to an object of type RegistrationLlegada. When a greyhound reaches the goal, it accesses the counter located on said object (whose initial value is 1), and takes that value as its arrival position, and then increases it by 1. The greyhound that manages to take the '1' will be the winner.

When starting the application, there is a first obvious error: the results (total run and number of the winning greyhound) are shown before the end of the race as such. However, once this is corrected, there may be more inconsistencies caused by the presence of race conditions.

## Solution part II

> [Parte 2](parte2)

## Part III

1. Fix the application so that the results notice is shown only when the execution of all the ‘greyhound’ threads is finished. For this keep in mind:

      1. The action of starting the race and showing the results is carried out from line 38 of MainCanodromo.

      2. The join() method of the Thread class can be used to synchronize the thread that starts the race, with the completion of the greyhound threads.

2. Once the initial problem has been corrected, run the application several times, and identify the inconsistencies in the results of the same by seeing the ‘ranking’ shown on the console (sometimes valid results could appear, but in other cases such inconsistencies may occur). From this, identify the critical regions of the program.

3. Use a synchronization mechanism to ensure that these critical regions only access one thread at a time. Verify the results.

4. Implement the pause and continue functionalities. With these, when "Stop" is clicked, all the threads of the greyhounds should fall asleep, and when "Continue" is clicked they should wake up and continue with the race. Design a solution that allows you to do this using the synchronization mechanisms with the Locks primitives provided by the language (wait and notifyAll).


