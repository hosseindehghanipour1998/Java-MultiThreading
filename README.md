# OS-Lab-2019

Dear Viewer ,
Hi.

Here I add all the files and codes that I am asked by the TA in OS Lab Course.

This course is taught by Mr.Mohammad Mostafaee (https://github.com/mmd-mostafaee) @Shiraz University 



# Session 1

We are told to write a program that runs multiple times with mutiple number of threads in order to add the elements of an integer array.
The goal of this task is only to monitor the effect of multi-threading on the OS and it's resources.
We were ought to write our results in a text file and show the minimum and average time for each time.
As we can see, The ideal number of threads for each task depends on the size of out integer array.
Semaphore and locking on a variable was not our main concern here and that's why we have ignored the critical section made by using the same variable for all of threads which causes mutual exclusion phenomenon.


# Session 2
In this session we were told to multiply two vectors ( 1xN & Nx1 ) and calculate their summation. As you can see, there are some faults made by not locking on the varibale which is the same for all threads. The main goal of this task was to see how the size of the vectors effect on the overall time of the program. As we can see, the bigger the vectors are the more devastating out overall time gets. As we can also see is that the ideal number of threads change due to the size of the vectors.


# Session 4
In this session we were asked to do 3 Tasks :

 Part I :

1 - extend the code of previous session ; by simply extending the Adder's inside loop counter from 500  to 1 Million repeats and decreasing the number of threads by {1,2} threads and removing the function "Thread.Sleep()" from the "Adder" class.Compare the average time for 1 thread with 2 threads and write it down in the report file.


2 - Put a locker on the overlapping variable " Adder.Summation " and call the locker.lock() and locker.unlock() before and after the " Summation++ " respectively and run the code with only 2 threads.Now compare the resulting time with part 1.( use "ReentrantLock" class for the locker)


Part II : 

1 - Now by using 2 semaphores - producerSemaphore and consumerSemaphore - and s Stack, write a program that has 5 threads of each class "Adder and Consumer " and seamphores with " permit = 3 " and you should also use "locker" to lock on the stack. Run the program for 10 times and each time the producer semaphores must push a random number into the stack and the consumer semaphores must pop a number from the stack. After pushing or popping, the program shall print the id and amount pushed/popped to/from the stack.

