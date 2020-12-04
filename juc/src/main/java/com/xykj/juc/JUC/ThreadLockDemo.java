package com.xykj.juc.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目:现在两个线程，可以操作初始值为0的一个变量，
 *  * 实现一个线程对该变量加1，一个线程对该变量减1，
 *  * 交替实现，来10轮，变量归0
 *
 *  新版本
 * @Author: wm
 * @Date: 2020-12-03  9:39
 * @Version 1.0
 */

//1.编写资源类
class Operation{
    private Integer number  = 0;
    private Lock lock = new ReentrantLock();
    //用于唤醒和等待，代替wait和notify
    private Condition condition = lock.newCondition();

    //编写操作资源类的方法
    //加1操作
    public void plusOne(){
        //加锁
        lock.lock();
        try {
            //判断
            while(number%2==0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //解锁
            lock.unlock();
        }
    }

    public void desOne(){
        lock.lock();
        try {
            while (number % 2 != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class ThreadLockDemo {
    public static void main(String[] args) {
        Operation operation = new Operation();
        new Thread(()->{ for (int i = 0; i < 10; i++) operation.plusOne(); },"A").start();
        new Thread(()->{ for (int i = 0; i < 10; i++) operation.desOne(); },"B").start();
    }
}
