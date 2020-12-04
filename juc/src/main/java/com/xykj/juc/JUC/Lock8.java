package com.xykj.juc.JUC;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wm
 * @Date: 2020-12-03  13:40
 * @Version 1.0
 * <p>
 * 多线程8锁
 * 1.标准访问，请问先打印邮件还是先打印短信
 * 2.邮件方法暂停4秒，请问先打印邮件还是短信
 * 3.新增一个普通hello(),请问先打印邮件还是hello
 * 4.两部手机，先打印邮件还是先打印短信
 * 5.两个静态同步方法，同一部手机，请问先打印邮件还是短信
 * 6.两个静态同步方法，两部手机，请问先打印邮件还是短信
 * 7.一个静态同步方法，一个普通同步方法，同一部手机，请问先打印邮件还是短信
 * 8.一个静态同步方法，一个普通同步方法，两部手机，请问先打印邮件还是短信
 */

class Iphone {

    public static synchronized void sendEmail() {
        try {
            //java8新的睡眠类，可以操作纳秒--->天之间的时间单位
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("send...email");
    }

    public  synchronized void sendMessage() {
        System.out.println("send...message");
    }

    public void hello(){
        System.out.println("hello");
    }
}

public class Lock8 {
    public static void main(String[] args) throws Exception {
        Iphone iphone = new Iphone();
        Iphone iphone2 = new Iphone();
        new Thread(() -> {
            iphone.sendEmail();
        }, "A").start();

        Thread.sleep(100);

        new Thread(() -> {
            //iphone.hello();
            //iphone.sendMessage();
            iphone2.sendMessage();
        }, "B").start();

    }
}
