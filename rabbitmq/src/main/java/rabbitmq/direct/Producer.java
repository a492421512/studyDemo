package rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.util.RabbitUtil;

import java.io.IOException;

/**
 * 订阅模式 生产者
 * @Author: wm
 * @Date: 2020-09-08  20:09
 * @Version 1.0
 */
public class Producer {
    public static void main(String[] args) throws IOException {
        //创建连接
        Connection connection = RabbitUtil.getConnection();
        //获取信道
        Channel channel = connection.createChannel();
        //声明交换机与类型 direct类型 订阅模式
        channel.exchangeDeclare("logs_direct","direct");
        //路由名称
        String route = "directType";
        //发送消息
        channel.basicPublish("logs_direct",route,null,"这是一个订阅摩尔是的消息".getBytes());
        //关闭
        RabbitUtil.closeChannelAndConnection(channel,connection);
    }
}
