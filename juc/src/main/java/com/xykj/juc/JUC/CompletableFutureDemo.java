package com.xykj.juc.JUC;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步回调
 *
 * @Author: wm
 * @Date: 2020-12-11  16:06
 * @Version 1.0
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步调用 没有返回值
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> System.out.println("这个是没有返回值的"));
        completableFuture.get();

        //异步回调
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("我有返回值");
            int a = 10/0;
            return 1024;
        });

        System.out.println("=============================");

        //当异步线程完成时
        System.out.println(supplyAsync.whenComplete((t, u) -> {
            System.out.println("------t" + t); //打印正确的值
            System.out.println("------u" + u); //打印错误方法
        }).exceptionally(f -> { //当出现异常时
            System.out.println("------exception" + f.getMessage());
            return 444;
        }).get());
    }
}
