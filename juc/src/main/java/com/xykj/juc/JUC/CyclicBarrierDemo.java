package com.xykj.juc.JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: wm
 * @Date: 2020-12-08  19:49
 * @Version 1.0
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        //public CyclicBarrier(int parties, Runnable barrierAction)
        //CountDownLatch是做减法，CyclicBarrier是做加法。
        //当运行7个线程后再运行此线程，14个线程后也会再运行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{System.out.println("召唤神龙");});

        for (int i = 0; i < 14; i++) {
            final int count = i;
            new Thread(()->{
                //lambda表达式只能用final修饰的变量
                System.out.println("召唤第"+count+"个龙珠");
                try {
                    //判断是否达到指定线程数量。
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
