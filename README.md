# OS-Lab-2019

Dear Viewer ,
Hi.

Here I add all the files and codes that I am asked by the TA in OS Lab Course.

This course is taught by Mr.Mohammad Mostafaee (@mmd-mostafaee) @Shiraz University 



# Session 1

We are told to write a program that runs multiple times with mutiple number of threads in order to add the elements of an integer array.
The goal of this task is only to monitor the effect of multi-threading on the OS and it's resources.
We were ought to write our results in a text file and show the minimum and average time for each time.
As we can see, The ideal number of threads for each task depends on the size of out integer array.
Semaphore and locking on a variable was not our main concern here and that's why we have ignored the critical section made by using the same variable for all of threads which causes mutual exclusion phenomenon.


# Session 2
In this session we were told to multiply two vectors ( 1*n & n*1 ) and calculate their summation. As you can see, there are some faults made by not locking on the varibale which is the same for all threads. The main goal of this task was to see how the size of the vectors effect on the overall time of the program. As we can see, the bigger the vectors are the more devastating out overall time gets. As we can also see is that the ideal number of threads change due to the size of the vectors.
