package rabbitmq.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.util.RabbitUtil;

import java.io.IOException;

/**
 *
 * @Author: wm
 * @Date: 2020-09-11  17:29
 * @Version 1.0
 */
public class TopicProducer {
    public static void main(String[] args) throws IOException {
        //创建连接
        Connection connection = RabbitUtil.getConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //绑定交换机
        channel.exchangeDeclare("topics","topic");

        String routeKey = "topicType.user.save";
        //绑定交换机和路由
        channel.basicPublish("topics",routeKey,null,("这是一个通配符订阅模式的消息"+routeKey).getBytes());
        //关闭连接和信道
        RabbitUtil.closeChannelAndConnection(channel,connection);
    }
}
