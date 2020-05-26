import com.rabbitmq.client.*;
import util.ConnectionUtil;

import java.io.IOException;

public class Recv2 {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        final Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DefaultConsumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String msg = new String(body);
                System.out.println("[x] receied: " + msg + "!");

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
