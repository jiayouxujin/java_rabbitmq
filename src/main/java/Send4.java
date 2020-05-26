import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

public class Send4 {

    private final static String EXCHANGE_NAME="test_direct_exchange";

    public static void main(String[] args) throws Exception {
        Connection connection= ConnectionUtil.getConnection();

        Channel channel=connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String message="注册成功，请短信回复[T]退订";

        channel.basicPublish(EXCHANGE_NAME,"sms",null,message.getBytes());
        System.out.println("[x] sent"+message);
        channel.close();
        connection.close();
    }
}
