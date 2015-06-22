package com.mycompany.multithreadcallablerunnablethread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 *
 * @author jupiter
 */
public class MultiThreadExample {

    public static void main(String[] args) {
        //启动子线程
        new JoinThread("新线程").start();
        for (int i = 0; i < 100; i++) {
            if (i == 20) {
                JoinThread jt = new JoinThread("被Join的线程");
                jt.start();
                //main线程调用了jt线程的join方法，main线程
                //必须等jt执行结束才会向下执行
                try {
                    jt.join();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "  " + i);
        }

        DaemonThread t = new DaemonThread();
        //将此线程设置成后台线程
        t.setDaemon(true);
        //启动后台线程
        t.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()
                    + "  " + i);
        }
        //-----－程序执行到此处，前台线程（main线程）结束------
        //后台线程也应该随之结束
        
    }
}

class JoinThread extends Thread {

    //提供一个有参数的构造器，用于设置该线程的名字
    public JoinThread(String name) {
        super(name);
    }

    //重写run方法，定义线程执行体
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + "  " + i);
        }
    }
}
class DaemonThread extends Thread
{
	//定义后台线程的线程执行体与普通线程没有任何区别
	public void run()
	{
		for (int i = 0; i < 1000 ; i++ )
		{
			System.out.println(getName() + "  " + i);
		}
	}
}