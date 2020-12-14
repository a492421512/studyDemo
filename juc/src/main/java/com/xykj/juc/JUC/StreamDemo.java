package com.xykj.juc.JUC;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @Author: wm
 * @Date: 2020-12-11  9:50
 * @Version 1.0
 *
 * 请按照给出的数据，找出满足以下条件的用户，也即以下条件全部满足
 * 偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序只输出一个用户名
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true) //链式编程
class User{
    private Integer id;
    private String userName;
    private Integer age;
}

public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(11,"a",23);
        User u2 = new User(12,"b",24);
        User u3 = new User(13,"c",22);
        User u4 = new User(14,"d",28);
        User u5 = new User(16,"e",27);

        List<User> users = Arrays.asList(u1, u2, u3, u4, u5);
        users.stream()
                .filter(u->u.getId() % 2 == 0)//过滤出偶数ID
                .filter(u->u.getAge() > 24)//过滤出年龄大于24
                .map(u->u.getUserName().toUpperCase())//用户名转为大写
                .sorted((o1,o2)->o2.compareTo(o1))//倒叙排序
                .limit(1)//只去一个值
                .forEach(System.out::println);
    }
}
