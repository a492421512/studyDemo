package com.sinofo;

import java.time.LocalDate;

/**
 * @Author: wm
 * @Date: 2020-04-27  10:53
 * @Version 1.0
 */
public class TestFun {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate lastDay = LocalDate.parse("2020-08-07");
        long day = today.toEpochDay() - lastDay.toEpochDay();
        System.out.println(day);
        boolean restore = false;
        if (day >= 90) {
            restore = true;
        }
        System.out.println(restore);
    }



}
