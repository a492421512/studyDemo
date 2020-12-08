package com.xykj.juc.JUC;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 * @Author: wm
 * @Date: 2020-12-08  20:29
 * @Version 1.0
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    //获得资源
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"获得车位");
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放资源
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }
    }
}
