package com.xykj.juc.JUC;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 四大函数式接口
 * @Author: wm
 * @Date: 2020-12-11  10:23
 * @Version 1.0
 */
public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        String str = "周润发";
        //消费型

        Consumer<String> consumer = System.out::println;
        consumer.accept(str);
        System.out.println("=====================");

        //供给型
        Supplier<String> supplier = ()->{return str;};
        System.out.println(supplier.get());
        System.out.println("=====================");

        //函数型
        Function<String,Integer> function = s -> {return s.length();};
        System.out.println(function.apply(str));
        System.out.println("=====================");

        //断定型
        Predicate<String> predicate = s->{return s.isEmpty();};
        System.out.println(predicate.test(str));

    }
}
