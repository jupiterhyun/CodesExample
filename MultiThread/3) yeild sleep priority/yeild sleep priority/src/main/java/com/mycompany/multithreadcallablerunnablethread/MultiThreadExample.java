package com.mycompany.multithreadcallablerunnablethread;

import java.util.Date;

/**
 *
 * @author jupiter
 */
public class MultiThreadExample {

    public static void main(String[] args) {
        //*******************yield
        //启动两条并发线程
        TestYield ty1 = new TestYield("高级");
        //将ty1线程设置成最高优先级
        ty1.setPriority(Thread.MAX_PRIORITY);
        ty1.start();
        TestYield ty2 = new TestYield("低级");
        //将ty1线程设置成最低优先级
        ty2.setPriority(Thread.MIN_PRIORITY);
        ty2.start();

        try {
            //************************sleep
            for (int i = 0; i < 10; i++) {
                System.out.println("当前时间: " + new Date());
                //调用sleep方法让当前线程暂停1s。
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //***************************优先级
        //改变主线程的优先级
        Thread.currentThread().setPriority(6);
        for (int i = 0; i < 30; i++) {
            if (i == 10) {
                PriorityTest low = new PriorityTest("低级");
                low.start();
                System.out.println("创建之初的优先级:" + low.getPriority());
                //设置该线程为最低优先级
                low.setPriority(Thread.MIN_PRIORITY);
            }
            if (i == 20) {
                PriorityTest high = new PriorityTest("高级");
                high.start();
                System.out.println("创建之初的优先级:" + high.getPriority());
                //设置该线程为最高优先级
                high.setPriority(Thread.MAX_PRIORITY);
            }
        }
    }
}

class TestYield extends Thread {

    public TestYield() {
    }

    public TestYield(String name) {
        super(name);
    }

    //定义run方法作为线程执行体
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(getName() + "  " + i);
            //当i等于20时，使用yield方法让当前线程让步
            if (i == 20) {
                Thread.yield();
            }
        }
    }
}

class PriorityTest extends Thread {

    public PriorityTest() {
    }

    //定义一个有参数的构造器，用于创建线程时指定name
    public PriorityTest(String name) {
        super(name);
    }

    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(getName() + ",其优先级是："
                    + getPriority() + ",循环变量的值为:" + i);
        }
    }
}
