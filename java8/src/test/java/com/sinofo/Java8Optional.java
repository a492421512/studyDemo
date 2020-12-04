package com.sinofo;

import java.util.Optional;

/**
 * @Author: wm
 * @Date: 2020-04-26  16:56
 * @Version 1.0
 */
public class Java8Optional {
    public static void main(String[] args) {
        Integer a = null;
        Integer b = 3;
        Optional<Integer> opA = Optional.ofNullable(a);
        Optional<Integer> opB = Optional.ofNullable(b);
        System.out.println(sum(opA,opB));
    }


    private static Integer sum( Optional<Integer> a,Optional<Integer> b){
        System.out.println("a参数是否存在"+a.isPresent());
        System.out.println("b参数是否存在"+b.isPresent());
        Integer a1 = a.orElse(0);
        return a1+b.get();
    }

}
