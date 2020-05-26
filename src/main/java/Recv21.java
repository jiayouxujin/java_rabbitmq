import com.rabbitmq.client.*;
import util.ConnectionUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

public class Recv21 {
    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                long deliveryTag = envelope.getDeliveryTag();
                String msg = new String(body, "utf-8");

                System.out.println("[消费者1] received: " + msg);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                channel.basicAck(deliveryTag, false);
            }
        };
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
