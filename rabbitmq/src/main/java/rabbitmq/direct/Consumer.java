package rabbitmq.direct;

import com.rabbitmq.client.*;
import rabbitmq.util.RabbitUtil;

import java.io.IOException;

/**
 * 订阅模式 消费者
 *
 * @Author: wm
 * @Date: 2020-09-08  20:09
 * @Version 1.0
 */
public class Consumer {
    public static void main(String[] args) throws IOException {
        //创建连接
        Connection connection = RabbitUtil.getConnection();
        //获取信道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare("logs_direct", "direct");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //绑定队列、交互机、路由
        //路由名称
        String route = "directType";
        channel.queueBind(queue, "logs_direct", route);
        //消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1" + new String(body));
            }
        });
    }
}
