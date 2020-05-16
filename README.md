# Java MultiThreading

Dear Viewer ,
Hi.

Here I add all the files and codes that I am asked by the TA in ___OS Lab Course in 2019___.

This course is taught by [Mr.Mohammad Mostafaee](https://github.com/mmd-mostafaee) @Shiraz University 



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


# Session 5
1 - We have another function called tryLock() in java. Code the " Dining Philosophers " problem and show all the possible incidents :
    A - A philosopher is eating.
    B - A philosopher is sleeping.
    C - A philosopher wants to eat but can not eat.
Rules :
    A - If a philosopher can not eat or doen't want to eat should sleep.
    B - There must be no starvation.
    C - Unlimited Food is provided for all of the philosophers.
    D - Each philosopher has his own plate.
    E - The limit on their turn is the available fork or spoon.
    F - The Code should run for unlimited time but I assigned a Loop = 5 in the thread run to see the results for limited time.
    
    
# Session 6
 Implement Semaphore Class by Using Locks ( Reentrant Lock )
 
 # Session 7 
 BarberShop Problem :
We have a barber named "Bob" who works in his barbershop. This barbershop has 4 chairs which means we can have the capacity of 4 new clients(customers).Barber Bob can have only 1 client's hair done at once, Which means he can have 4 customers sitting at the reserved chairs and 1 customer sitting at the barber chair to have his hair done.When there are no clients available, the barber would go and rest untill a new customer arrives and awakens the barber.Implement this problem and notice that :

1 - Barber , Barbershop , Client are 3 distinct classes.

2 - Barber and Client have semaphores.

3 - The code must be fair for all customer which means older customers get to be serviced sooner than new comers.( You shall keep a Queue of clients in the Barbershop class )

4 - At each stage , print what is exactly happening.

# Session 8
Assume a cigarette requires three ingredients to make and smoke: tobacco, paper, and matches. There are three smokers around a table, each of whom has an infinite supply of one of the three ingredients — one smoker has an infinite supply of tobacco, another has paper, and the third has matches.

There is also a non-smoking agent who enables the smokers to make their cigarettes by arbitrarily (non-deterministically) selecting two of the supplies to place on the table. The smoker who has the third supply should remove the two items from the table, using them (along with the own supply) to make a cigarette, which they smoke for a while. Once the smoker has finished his cigarette, the agent places two new random items on the table. This process continues forever.
implement the code by only using Monitor.
