package com.xykj.juc.JUC;

import java.util.concurrent.CountDownLatch;

/**
 * 如何保证其他线程执行完毕之后，再结束主线程
 * @Author: wm
 * @Date: 2020-12-08  11:41
 * @Version 1.0
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t离开了教室");
                //类似倒计时 -1
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //如果没有到0会继续等待
        countDownLatch.await();
        System.out.println("主线程执行结束");
    }
}
