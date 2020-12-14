package com.xykj.juc.JUC;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 分支合并。
 *
 * 要求算出0-100的和
 */
class MyTask extends RecursiveTask<Integer>{

    //设置固定值
    private final Integer NATIONAL_VALUE = 10;

    private  int begin;
    private int end;
    private int result;

    public MyTask(Integer begin, Integer end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        //如果在10以内的数值直接计算
        if (end-begin <= NATIONAL_VALUE){
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        }else {
            //进行任务拆分
            int middle = (begin+end)/2;
            MyTask task01 = new MyTask(begin,middle);
            MyTask task02 = new MyTask(middle+1,end);
            task01.fork();
            task02.fork();
            result = task01.join()+task02.join();
        }
        return result;
    }
}


public class ForkJoinDemo {

    public static void main(String[] args) throws Exception {
        MyTask task = new MyTask(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //提交方法
        ForkJoinTask<Integer> submit = forkJoinPool.submit(task);
        //获取结果
        System.out.println(submit.get());
        //关闭线程池
        forkJoinPool.shutdown();
    }
}
