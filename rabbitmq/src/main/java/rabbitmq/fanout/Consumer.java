package rabbitmq.fanout;

import com.rabbitmq.client.*;
import rabbitmq.util.RabbitUtil;

import java.io.IOException;

/**
 * fanout消费者
 * @Author: wm
 * @Date: 2020-09-08  19:36
 * @Version 1.0
 */
public class Consumer {

    public static void main(String[] args) throws IOException {
        //创建连接
        Connection connection = RabbitUtil.getConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //绑定交换机
        channel.exchangeDeclare("logs","fanout");
        //创建临时队列
        String queue = channel.queueDeclare().getQueue();
        //绑定交换机和队列
        //1.队列名 2.交换机名称 3.路由
        channel.queueBind(queue,"logs","");
        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1"+new String(body)+queue);
            }
        });
    }
}
