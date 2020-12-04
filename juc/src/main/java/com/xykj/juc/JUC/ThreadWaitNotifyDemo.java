package com.xykj.juc.JUC;

/**
 * @Author: wm
 * @Date: 2020-12-02  14:40
 * @Version 1.0
 *
 * 题目:现在两个线程，可以操作初始值为0的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 交替实现，来10轮，变量归0
 *
 * 1.线程操作资源类
 *      先写资源类
 *      写操作资源类的方法
 *
 * 2.线程之间的通讯
 *   判断/干活/通知
 *
 * 3.多线程交互中，必须防止多线程的虚假唤醒，也即判断只能用while ,不能用if
 */

//资源类
class SourceNumber{
    private int number = 0;

    synchronized void plusOne() throws InterruptedException {
        //判断
        while (number != 0){
            this.wait();
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //通知
        this.notifyAll();
    }

    synchronized void desOne() throws InterruptedException {
        while (number == 0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }
}


public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        SourceNumber sourceNumber = new SourceNumber();
        //加1线程
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sourceNumber.plusOne();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"A").start();
        //减1线程
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sourceNumber.desOne();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"B").start();

        //加1线程
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sourceNumber.plusOne();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"C").start();
        //减1线程
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    sourceNumber.desOne();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"D").start();
    }
}
