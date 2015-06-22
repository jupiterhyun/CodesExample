package com.mycompany.multithreadcallablerunnablethread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 *
 * @author jupiter
 */
public class MultiThreadExample {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //调用Thread的currentThread方法获取当前线程
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                //创建、并启动第一条线程
                new FirstThread().start();
                //创建、并启动第二条线程
                new FirstThread().start();
            }
        }
        System.out.println("*********************************************************");
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            if (i == 20) {
                SecondThread st = new SecondThread();
                //通过new Thread(target , name)方法创建新线程
                new Thread(st, "新线程1").start();
                new Thread(st, "新线程2").start();
            }
        }
        System.out.println("*********************************************************");

        ThirdThread rt = new ThirdThread();
        FutureTask<Integer> task = new FutureTask<Integer>(rt);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            if (i == 20) {
                //通过new Thread(target , name)方法创建新线程
                new Thread(task, "有返回值！！").start();
            }
        }
        try{
            System.out.println("返回值： " + task.get());
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

class FirstThread extends Thread {

    private int i;

    //重写run方法，run方法的方法体就是线程执行体
    public void run() {
        for (; i < 100; i++) {
            //当线程类继承Thread类时，可以直接调用getName()方法来返回当前线程的名。
            //如果想获取当前线程，直接使用this即可
            //Thread对象的getName返回当前该线程的名字
            System.out.println(getName() + " " + i);
        }
    }

}

class SecondThread implements Runnable {

    private int i;

    //run方法同样是线程执行体
    public void run() {
        for (; i < 100; i++) {
            //当线程类实现Runnable接口时，
            //如果想获取当前线程，只能用Thread.currentThread()方法。
            System.out.println(Thread.currentThread().getName() + "  " + i);
        }
    }
}

class ThirdThread implements Callable<Integer> {

    private int i;

    //run方法同样是线程执行体
    @Override
    public Integer call() {
        for (; i < 100; i++) {
            //当线程类实现Runnable接口时，
            //如果想获取当前线程，只能用Thread.currentThread()方法。
            System.out.println(Thread.currentThread().getName() + "  " + i);
        }
        return i;
    }
}
