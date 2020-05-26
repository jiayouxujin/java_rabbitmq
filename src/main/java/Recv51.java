import com.rabbitmq.client.*;
import util.ConnectionUtil;

public class Recv51 {
    private final static String QUEUE_NAME="topic_exchange_queue_01";
    private final static String EXCHANGE_NAME="test_topic_exchange";

    public static void main(String[] args) throws Exception {
        Connection connection= ConnectionUtil.getConnection();

        Channel channel=connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"*.orange.*");

        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,byte[] body) {
                String msg=new String(body);
                System.out.println("[消费者1] received"+msg);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
