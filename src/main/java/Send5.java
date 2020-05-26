import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

public class Send5 {
    private final static String EXCAHGNE_NAME="test_topic_exchange";

    public static void main(String[] args) throws Exception {
        Connection connection= ConnectionUtil.getConnection();

        Channel channel=connection.createChannel();

        channel.exchangeDeclare(EXCAHGNE_NAME, BuiltinExchangeType.TOPIC);

        String message="这是一只行动迅速的橙色的兔子";

        channel.basicPublish(EXCAHGNE_NAME,"quick.orange.rabbit",null,message.getBytes());
        System.out.println("[动物描述] Sent"+message);
        channel.close();
        connection.close();
    }
}
