package com.xykj.juc.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: wm
 * @Date: 2020-12-03  10:44
 * @Version 1.0
 *
 * 多线程之间顺序调用 ，实现A-B-C
 * 三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 来10轮
 */
class ShareResource{
    //定义规则 A=1,B=2,C=3
    private Integer number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            //判断不是A，就让A等待
            while (number != 1){
                condition1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+i);
            }
            //A结束后是B,唤醒B
            number=2;
            //精确唤醒
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            //判断不是B，就让B等待
            while (number != 2){
                condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+i);
            }
            //B结束后是C,唤醒C
            number=3;
            //精确唤醒
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            //判断不是B，就让B等待
            while (number != 3){
                condition3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+i);
            }
            //B结束后是C,唤醒C
            number=1;
            //精确唤醒
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}


public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{ for (int i = 0; i < 10; i++) shareResource.print5(); },"A").start();
        new Thread(()->{ for (int i = 0; i < 10; i++) shareResource.print10(); },"B").start();
        new Thread(()->{ for (int i = 0; i < 10; i++) shareResource.print15(); },"C").start();
    }
}
