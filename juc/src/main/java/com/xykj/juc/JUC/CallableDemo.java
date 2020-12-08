package com.xykj.juc.JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 *
 * Callable接口
 *
 * Callable 和 Runnable 的区别
 *  1.是否有返回值
 *  2.是否可以抛出异常
 *  3.一个是call()方法 一个是run()方法
 *
 *
 * @Author: wm
 * @Date: 2020-12-08  9:29
 * @Version 1.0
 */

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("come in here");
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws Exception{
        //因为new Thread只能穿Runnable接口或者Thread的子类，不能直接传Callable接口，所以找一个
        //中间接口，即实现了Runnable接口又跟Callable接口有关系。
        FutureTask futureTask = new FutureTask(new MyThread());
        //因为只有一个FutureTask实例，Java会自动缓存，线程的方法只会执行一次
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"A").start();

        System.out.println(Thread.currentThread().getName()+"主线程结束");
        //获取Callable接口的返回值（要把此方法放到最后，避免造成阻塞影响主线程的执行--->先做容易的题，再做最难的）
        System.out.println(futureTask.get());
    }
}
