import com.rabbitmq.client.*;
import com.sun.org.apache.bcel.internal.generic.FADD;
import util.ConnectionUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Recv22 {

    private final static String QUQUE_NAME = "test_work_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUQUE_NAME, false, false, false, null);

        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("[消费者2] received" + msg);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUQUE_NAME, false, consumer);
    }
}
