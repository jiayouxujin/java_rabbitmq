import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sun.corba.se.pept.transport.ConnectionCache;
import util.ConnectionUtil;

public class Send3 {
    private final static String EXCHANGE_NAME="test_fanout_exchange";

    public static void main(String[] args) throws Exception {
        Connection connection= ConnectionUtil.getConnection();

        Channel channel=connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        String message="注册成功!";

        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());

        System.out.println("[生产者]Sent"+message);
        channel.close();
        connection.close();
    }
}
