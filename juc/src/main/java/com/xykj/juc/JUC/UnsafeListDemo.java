package com.xykj.juc.JUC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * list为什么是线程不安全的
 *  1、故障现象
 *      java.util.ConcurrentModificationException 并发修改异常
 * 2、导致原因
 *
 * 3、解决方案
 *      3.1 vector
 *      3.2 Collections.synchronizedList
 *      3.3 CopyOnWriteArrayList
 */
public class UnsafeListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
