package rabbitmq.fanout;

import com.rabbitmq.client.*;
import rabbitmq.util.RabbitUtil;

import java.io.IOException;

/**
 * @Author: wm
 * @Date: 2020-09-08  19:52
 * @Version 1.0
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {

        Connection connection = RabbitUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare("logs", "fanout");

        String queue = channel.queueDeclare().getQueue();

        channel.queueBind(queue,"logs","");

        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2"+new String(body)+queue);
            }
        });
    }
}
