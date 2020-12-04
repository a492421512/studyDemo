package com.xykj.juc.JUC;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: wm
 * @Date: 2020-12-01  16:42
 * @Version 1.0
 *
 *
 * 题目 三个售票员 卖出 30张票
 *
 *  多线程编程的企业级套路+模板
 *
 *  1.在高内聚低耦合的前提下，线程    操作    资源类
 *
 *  ①先写一个资源类
 *  ②编写资源类操作的方法（例如空调自带制冷制热）
 *  ③创建线程调用资源类
 */

//①先写一个资源类
class Ticket{

    private Integer number = 30;
    //Lock锁是接口，new他下面的实现类ReentrantLock
    private Lock lock = new ReentrantLock();
    //②编写资源类操作的方法（卖票）
    //老版本写法
    /*public synchronized void saleTicket(){
        if (number > 0 ){
            System.out.println(Thread.currentThread().getName()+"卖出"+(number--)+"还剩余"+number);
        }
    }*/
    public void saleTicket(){
        //加锁
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出" + (number--) + "还剩余" + number);
            }
        }finally{
            //释放锁
            lock.unlock();
        }
    }
}

public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{for(int i = 0;i <= 40;i++) ticket.saleTicket();},"A").start();
        new Thread(()->{for(int i = 0;i <= 40;i++) ticket.saleTicket();},"B").start();
        new Thread(()->{for(int i = 0;i <= 40;i++) ticket.saleTicket();},"C").start();
        /*Thread t1 = new Thread(()->{
            for (int i = 0; i < 40; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                ticket.saleTicket();
            }
        },"售票员1");

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        },"售票员2");

        Thread t3 = new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        },"售票员3");
        t1.start();
        t2.start();
        t3.start();*/
    }
}


class Sell{


}