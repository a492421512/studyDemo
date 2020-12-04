package rabbitmq.topics;

import com.rabbitmq.client.*;
import rabbitmq.util.RabbitUtil;

import java.io.IOException;

/**
 * 通配符订阅模式
 * @Author: wm
 * @Date: 2020-09-11  17:28
 * @Version 1.0
 */
public class TopicsConsumer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare("topics","topic");
        //创建一个临时队列
        String queue = channel.queueDeclare().getQueue();
        String routeKey = "topicType.#";
        //绑定队列、交换机、路由
        channel.queueBind(queue,"topics",routeKey);
        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }
}
