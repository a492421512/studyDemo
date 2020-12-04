package rabbitmq.workquene;

import com.rabbitmq.client.*;
import rabbitmq.util.RabbitUtil;

import java.io.IOException;

/**
 * 工作模式消费者2
 * @Author: wm
 * @Date: 2020-09-04  20:10
 * @Version 1.0
 */
public class WorkConsumer2 {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        channel.basicQos(1);//每次只消费一次
        //绑定队列：消费者和生产者的参数要一致
        channel.queueDeclare("work",true,false,false,null);
        //接受并处理消息
        //参数1：队列名
        //参数2：是否自动确认消息
        //参数3：消费时回调的接口
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费生产者2============"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        });
    }
}
