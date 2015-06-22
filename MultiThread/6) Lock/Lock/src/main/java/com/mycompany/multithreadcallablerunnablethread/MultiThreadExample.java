package com.mycompany.multithreadcallablerunnablethread;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;
/**
 *
 * @author jupiter
 */
public class MultiThreadExample {

    public static void main(String[] args) {
        //创建一个账户
        Account acct = new Account("1234567", 1000);
        //模拟两个线程对同一个账户取钱
        new DrawThread("甲", acct, 800).start();
        new DrawThread("乙", acct, 800).start();
    }
}

class DrawThread extends Thread {

    //模拟用户账户
    private Account account;
    //当前取钱线程所希望取的钱数
    private double drawAmount;

    public DrawThread(String name, Account account,
            double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    //当多条线程修改同一个共享数据时，将涉及到数据安全问题。
    public void run() {
        account.draw(drawAmount);
    }
}

class Account {

    //定义锁对象
    private final ReentrantLock lock = new ReentrantLock();
    private String accountNo;
    private double balance;

    public Account() {
    }

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public double getBalance() {
        return this.balance;
    }

    public void draw(double drawAmount) {
        lock.lock();
        try {
            //账户余额大于取钱数目
            if (balance >= drawAmount) {
                //吐出钞票
                System.out.println(Thread.currentThread().getName()
                        + "取钱成功！吐出钞票:" + drawAmount);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                //修改余额
                balance -= drawAmount;
                System.out.println("\t余额为: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName()
                        + "取钱失败！余额不足！");
            }
        } finally {
            lock.unlock();
        }
    }

    public int hashCode() {
        return accountNo.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == Account.class) {
            Account target = (Account) obj;
            return target.getAccountNo().equals(accountNo);
        }
        return false;
    }
}
