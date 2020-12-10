package com.xykj.juc.JUC;

import java.util.concurrent.*;

/**
 * 线程池
 * @Author: wm
 * @Date: 2020-12-10  10:31
 * @Version 1.0
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {

        /*
        //线程池7大参数
        int corePoolSize, 核心线程池
        int maximumPoolSize,   最大线程池
        long keepAliveTime,     保留的时间
        TimeUnit unit,          TimeUnit参数
        BlockingQueue<Runnable> workQueue,  阻塞队列
        ThreadFactory threadFactory,    线程工厂一般使用默认的
        RejectedExecutionHandler handler    拒绝请求策略（）
            AbortPolicy(默认) 中止策略    超出可执行的线程数则会抛出异常 java.util.concurrent.RejectedExecutionException
            CallerRunsPolicy    回调策略    交由方法调用者自行处理
            DiscardPolicy   丢弃策略    直接丢弃并且不会通知
            DiscardOldestPolicy     喜新厌旧策略  丢弃队列最前面的任务，然后重新提交被拒绝的任务。
        */

        //线程池最多可以执行的线程为maximumPoolSize + workQueue  超出由拒绝策略执行
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                5,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i = 0; i < 10; i++) {
                //线程池执行
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //线程池关闭
            threadPool.shutdown();
        }
    }

    //在阿里的开发手册中是不允许用Java自带的API创建线程池，因为他们的最大线程数是Integer.MAX_VALUE,会导致OOM
    private static void initThreadPool() {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//线程池中有固定的五个线程，对应5个银行办理窗口
        //ExecutorService threadPool = Executors.newSingleThreadExecutor(); //线程池中有固定的1个线程，对应1个银行办理窗口
        ExecutorService threadPool = Executors.newCachedThreadPool();//线程池中有N个线程，对应N个银行窗口

        //模拟有10个顾客来银行办理业务，目前池子里有5个业务人员
        try {
            for (int i = 0; i < 10; i++) {
                //TimeUnit.MILLISECONDS.sleep(400);
                //线程池执行
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //线程池关闭
            threadPool.shutdown();
        }
    }
}
