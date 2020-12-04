package com.xykj.juc.JUC;

/**
 * Lambda 表达式
 * @Author: wm
 * @Date: 2020-12-02  13:44
 * @Version 1.0
 */

//函数式接口
@FunctionalInterface
interface Fun{
    public abstract String add(int a, String b);

    //默认方法,可以在函数式接口中编写无数个
    default int div(int a,int b){
        return a/b;
    }
    //静态方法,可以在函数接口中编写无数个
    static int mv(int a, int b){
        return a*b;
    }
}

public class LambdaExpress {

    public static void main(String[] args) {
        //拷贝小括号，写死右箭头，落地大括号
        Fun fun = (a,b)->{System.out.println("come on");
            return a+b;
        };
        //调用接口抽象方法
        System.out.println(fun.add(1, "2"));
        //调用接口默认方法
        System.out.println(fun.div(10, 5));
        //调用接口静态方法
        System.out.println(Fun.mv(1, 2));
    }
}
