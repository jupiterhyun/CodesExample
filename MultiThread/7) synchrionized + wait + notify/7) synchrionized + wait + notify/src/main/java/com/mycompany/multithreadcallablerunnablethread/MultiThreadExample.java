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
        Account acct = new Account("1234567", 0);
        new DrawThread("取钱者", acct, 800).start();
        new DepositThread("存款者甲", acct, 800).start();
        new DepositThread("存款者乙", acct, 800).start();
        new DepositThread("存款者丙", acct, 800).start();
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

    //重复100次执行取钱操作
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.draw(drawAmount);
        }
    }
}

class DepositThread extends Thread {

    //模拟用户账户

    private Account account;
    //当前取钱线程所希望存款的钱数
    private double depositAmount;

    public DepositThread(String name, Account account,
            double depositAmount) {
        super(name);
        this.account = account;
        this.depositAmount = depositAmount;
    }

    //重复100次执行存款操作
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.deposit(depositAmount);
        }
    }
}

class Account {

    private String accountNo;
    private double balance;
    //标识账户中是否已有存款的旗标
    private boolean flag = false;

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

    public synchronized void draw(double drawAmount) {
        try {
            //如果flag为假，表明账户中还没有人存钱进去，则取钱方法阻塞
            if (!flag) {
                wait();
            } else {
                //执行取钱
                System.out.println(Thread.currentThread().getName()
                        + " 取钱:" + drawAmount);
                balance -= drawAmount;
                System.out.println("账户余额为：" + balance);
                //将标识账户是否已有存款的旗标设为false。
                flag = false;
                //唤醒其他线程
                notifyAll();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void deposit(double depositAmount) {
        try {
            //如果flag为真，表明账户中已有人存钱进去，则存钱方法阻塞
            if (flag) {
                wait();
            } else {
                //执行存款
                System.out.println(Thread.currentThread().getName()
                        + " 存款:" + depositAmount);
                balance += depositAmount;
                System.out.println("账户余额为：" + balance);
                //将表示账户是否已有存款的旗标设为true
                flag = true;
                //唤醒其他线程
                notifyAll();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
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
