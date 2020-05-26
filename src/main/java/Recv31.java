import com.rabbitmq.client.*;
import util.ConnectionUtil;

public class Recv31 {
    private final static String QUEUE_NAME="fanout_exchange_queue_sms";

    private final static String EXCHANGE_NAME="test_fanout_exchange";

    public static void main(String[] args) throws Exception {
        Connection connection= ConnectionUtil.getConnection();

        Channel channel=connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        DefaultConsumer consumer=new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,byte[] body){
                String msg=new String(body);
                System.out.println("[短信服务] received"+msg);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
