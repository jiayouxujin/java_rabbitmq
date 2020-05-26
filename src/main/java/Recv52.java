import com.rabbitmq.client.*;
import util.ConnectionUtil;

public class Recv52 {
    private final static String QUEUE_NAME="topic_exchange_queue_02";
    private final static String EXCHANGE_NAME="test_topic_exchange";

    public static void main(String[] args) throws Exception {
        Connection connection= ConnectionUtil.getConnection();

        Channel channel=connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"*.*.rabbit");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"lazy.#");

        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,byte[] body){
                String msg=new String(body);
                System.out.println("[消费者2] received"+msg);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
