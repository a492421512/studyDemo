package rabbitmq.helloWord;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.util.RabbitUtil;

/**
 * @Author: wm
 * @Date: 2020-08-17  19:40
 * @Version 1.0
 */
public class Producer {

    public static void main(String[] args) throws Exception{
        /*//1.获取工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.连接主机
        connectionFactory.setHost("192.168.112.128");
        //3.设置端口号
        connectionFactory.setPort(5672);
        //4.设置连接的虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //5.设置访问的用户名和密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");*/

        //获取连接对象
        Connection connection = RabbitUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //绑定队列
        //参数1：队列名称
        //参数2：durable 用来定义队列是否需要持久化
        //参数3：exclusive 是否独占队列
        //参数4：autoDelete 在消费完成后是否删除队列
        //参数5：arguments 额外附加参数
        channel.queueDeclare("hello",false,false,false,null);
        //发布消息
        //参数1：交换机名称     参数2：队列名称    参数3：传递消息的额外设置   参数4：消息内容
        channel.basicPublish("","hello",null,"hello rabbitmq".getBytes());
        //关闭连接
        RabbitUtil.closeChannelAndConnection(channel,connection);
    }
}
