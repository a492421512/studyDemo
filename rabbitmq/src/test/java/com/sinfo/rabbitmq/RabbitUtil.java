package com.sinfo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * MQ工具类
 *
 * @Author: wm
 * @Date: 2020-09-03  20:14
 * @Version 1.0
 */
public class RabbitUtil {

    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        //2.连接主机
        connectionFactory.setHost("192.168.112.128");
        //3.设置端口号
        connectionFactory.setPort(5672);
        //4.设置连接的虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //5.设置访问的用户名和密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");
    }

    //获取连接
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道和连接
    public static void closeChannelAndConnection(Channel channel, Connection connection) {
        try {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
