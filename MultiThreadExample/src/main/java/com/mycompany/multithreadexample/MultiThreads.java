package com.mycompany.multithreadexample;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
/**
 *
 * @author jupiter
 */
public class MultiThreads {

    public static void main(String[] args) {
        //Use the executor created by the newCachedThreadPool() method
        //only when you have a reasonable number of threads
        //or when they have a short duration.
        
        /*
            newFixedThreadPool: this method returns a thread pool whose maximum size is fixed. 
        It will create new threads as needed up to the maximum configured size. 
        When the number of threads hits the maximum, the thread pool will maintain the size constant.
            newCachedThreadPool: this method returns an unbounded thread pool, that is a thread pool without a maximum size. 
        However, this kind of thread pool will tear down unused thread when the load reduces.
            newSingleThreadedExecutor: this method returns an executor that guarantees that tasks will be executed in a single thread.
        newScheduledThreadPool: this method returns a fixed size thread pool that supports delayed and timed task execution.
        */
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for (int i = 0; i <= 5; i++) {
            Task task = new Task("Task " + i);
            System.out.println("A new task has been added : " + task.getName());
            executor.execute(task);
        }
        executor.shutdown();
        System.out.println("*****************************************");
        executor = new ThreadPoolExecutor(
                2,//core thread
                5,//max thread
                0,//idle time
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()
                );
        for (int i = 6; i <= 10; i++) {
            Task task = new Task("Task " + i);
            System.out.println("A new task has been added : " + task.getName());
            executor.execute(task);
        }
         executor.shutdown();
         
         (new Thread(new Task("muhaha"))).start();
         (new Thread(new Task("heheheh"))).start();
         
    }
    
 
    
}

class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            Long duration = (long) (Math.random() * 10 % 10);
            System.out.println("Doing a task during : " + name);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
