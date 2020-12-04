package rabbitmq.helloWord;

import com.rabbitmq.client.*;
import rabbitmq.util.RabbitUtil;

import java.io.IOException;

/**
 * @Author: wm
 * @Date: 2020-09-03  19:53
 * @Version 1.0
 */
public class Consumer {
    public static void main(String[] args) throws Exception{
        System.out.println("开始接受消息-----------------");
        /*ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.112.128");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");*/

        //获取连接对象
        Connection connection = RabbitUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //绑定队列
        channel.queueDeclare("hello",false,false,false,null);
        //消费消息
        //参数1：队列名
        //参数2：是否自动确认消息
        //参数3：消费时回调的接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("获取消息===================>"+new String(body));
            }
        });

    }
}
