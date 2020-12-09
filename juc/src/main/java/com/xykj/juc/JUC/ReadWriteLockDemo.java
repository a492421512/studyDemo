package com.xykj.juc.JUC;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: wm
 * @Date: 2020-12-09  11:08
 * @Version 1.0
 */

class MyCache{

    private volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        //添加写锁
        readWriteLock.writeLock().lock();
        try {
            System.out.println("开始写入"+key);
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key,value);
            System.out.println("写入完成"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放写锁
            readWriteLock.writeLock().unlock();
        }



    }

    public void get(String key){
        //添加读锁
        readWriteLock.readLock().lock();
        try {
            System.out.println("开始读取"+key);
            TimeUnit.MILLISECONDS.sleep(300);
            Object value = map.get(key);
            System.out.println("读取完成--->"+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放写锁
            readWriteLock.readLock().unlock();
        }
    }


}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        //写入操作
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt+"");
            },"A").start();
        }

        //读取操作
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },"B").start();
        }
    }
}
