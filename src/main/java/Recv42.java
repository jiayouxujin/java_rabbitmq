import com.rabbitmq.client.*;
import util.ConnectionUtil;

public class Recv42 {
    private final static String QUEUE_NAME = "direct_exchange_queue_mail";
    private final static String EXCHANGE_NAME = "test_direct_exchange";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "email");

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                String msg = new String(body);
                System.out.println("[邮件服务] received " + msg);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
