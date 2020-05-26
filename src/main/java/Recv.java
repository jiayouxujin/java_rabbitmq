import com.rabbitmq.client.*;
import util.ConnectionUtil;

import java.io.UnsupportedEncodingException;

public class Recv {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DefaultConsumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {

                int i = 1 / 0;
                String exchange = envelope.getExchange();
                long deliveryTag = envelope.getDeliveryTag();

                String msg = new String(body, "utf-8");
                System.out.println("[x] reveived :" + msg + "!");
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
