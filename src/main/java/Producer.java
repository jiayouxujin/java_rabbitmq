import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer{
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");

        Connection conn=factory.newConnection();

        Channel channel=conn.createChannel();
        String exchangeName="hello-exchange";
        channel.exchangeDeclare(exchangeName,"direct",true);

        String routingKey="hola";

        byte[] messageBodyBytes="quit".getBytes();
        channel.basicPublish(exchangeName,routingKey,null,messageBodyBytes);

        channel.close();
        conn.close();
    }
}