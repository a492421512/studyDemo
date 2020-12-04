package rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.util.RabbitUtil;

import java.io.IOException;

/**
 * fanout 模式
 * @Author: wm
 * @Date: 2020-09-08  19:36
 * @Version 1.0
 */
public class Producer {
    public static void main(String[] args) throws IOException {
        //创建连接
        Connection connection = RabbitUtil.getConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //绑定交换机
        //参数1：交换机名称 参数2：交换机类型 fanout广播类型
        channel.exchangeDeclare("logs","fanout");
        //发送消息 1.交换机名称 2.路由名称 3.消息持久化 4.发送的消息
        channel.basicPublish("logs","",null,"fanout messages".getBytes());
        //关闭连接
        RabbitUtil.closeChannelAndConnection(channel,connection);
    }
}
