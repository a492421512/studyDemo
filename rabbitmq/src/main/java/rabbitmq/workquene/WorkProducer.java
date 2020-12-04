package rabbitmq.workquene;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import rabbitmq.util.RabbitUtil;

import java.io.IOException;

/**
 * 工作模式生产者
 * @Author: wm
 * @Date: 2020-09-04  20:03
 * @Version 1.0
 */
public class WorkProducer {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitUtil.getConnection();
        //建立通道
        Channel channel = connection.createChannel();
        //绑定队列
        //参数1：队列名称
        //参数2：durable 用来定义队列是否需要持久化
        //参数3：exclusive 是否独占队列
        //参数4：autoDelete 在消费完成后是否删除队列
        //参数5：arguments 额外附加参数
        channel.queueDeclare("work",true,false,false,null);
        //发送消息
        for (int i = 0; i < 10; i++) {
            //MessageProperties.PERSISTENT_TEXT_PLAIN 消息持久化
            channel.basicPublish("","work", MessageProperties.PERSISTENT_TEXT_PLAIN,(i+"work消息").getBytes());
        }

        //关闭连接
        RabbitUtil.closeChannelAndConnection(channel,connection);

    }
}
