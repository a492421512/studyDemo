package com.sinofo;

import com.sinofo.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: wm
 * @Date: 2020-04-26  14:01
 * @Version 1.0
 */
public class Java8Stream {
    public static void main(String[] args) {
        List<User> list = new ArrayList<User>();
        int id = 1;
        for (int i = 0; i < 10; i++) {
            User user = new User(id, "小明" + id, "男");

            if (i==9){
                //id=1;
                user = new User(id,"小红","女");
            }
            list.add(user);
            id++;
        }
        System.out.println(list);
        //抽取对象中所有id的集合
        List<Integer> idList = list.stream().map(User::getId).collect(Collectors.toList());
        System.out.println(idList);
        System.out.println("-------------------");
        //list转map id为Key user为value
        Map<Integer, User> map = list.stream().collect(Collectors.toMap(e -> e.getId(), e -> e));
        System.out.println(map.size());
        System.out.println(map);
        System.out.println("-------------------");
        //list转map 如果有重复的key取最后次的结果
        Map<Integer, User> map2 = list.stream().collect(Collectors.toMap(e -> e.getId(), e -> e, (key1, key2) -> key2));
        System.out.println(map2.size());
        System.out.println(map2);
        System.out.println("-------------------");
        //去重
        List<User> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("-------------------");
        //map转list
        List<User> mapToList = map.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(e -> new User(e.getKey(), e.getValue().getName(), e.getValue().getSex())).collect(Collectors.toList());
        System.out.println(mapToList);
        System.out.println("-------------------");
        //foreach遍历集合
        List<Integer> list1 = new ArrayList<>();
        list.forEach(user -> list1.add(user.getId()));
        System.out.println(list1);
        System.out.println("-------------------");
        //通过设置条件，得到过滤出来的数据
        list.stream().filter(user -> user.getId()==10).forEach(System.out::println);
        System.out.println("-------------------");
        //取前五条数据
        list.stream().limit(5).forEach(System.out::print);

        map.forEach((k,v)-> {
            list1.add(k);
            System.out.println(list1);
            System.out.println(v);
        });
        List<Integer> intList = new ArrayList<>();
        //通过设置条件，得到过滤出来的数据
        list.stream().filter(user -> user.getName().contains("小明")).forEach(user -> intList.add(user.getId()));
        System.out.println(intList);
    }

}
