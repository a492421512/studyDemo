package rabbitmq.workquene;

import com.rabbitmq.client.*;
import rabbitmq.util.RabbitUtil;

import java.io.IOException;

/**
 * 工作模式消费者1
 * @Author: wm
 * @Date: 2020-09-04  20:10
 * @Version 1.0
 */
public class WorkConsumer {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        channel.basicQos(1);//一次只消费一个消息
        //绑定队列：消费者和生产者的参数要一致
        channel.queueDeclare("work",true,false,false,null);
        //接受并处理消息
        //参数1：队列名
        //参数2：是否自动确认消息
        //参数3：消费时回调的接口
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    new Thread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费生产者1============"+new String(body));
                //消息确认
                //参数1：消息标示，参数2：手动确认 如果不确认，消息会遗留在队列中
                channel.basicAck(envelope.getDeliveryTag(),true);
        }
        });
    }
}
