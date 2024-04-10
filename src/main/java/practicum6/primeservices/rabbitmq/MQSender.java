package practicum6.primeservices.rabbitmq;

import org.apache.logging.log4j.message.MessageFormatMessage;

public class MQSender {
    private final Queue queue;
    private final RabbitTemplate rabbitTemplate;
    public MQSender(Queue queue,RabbitTemplate rabbitTemplate ){
        this.queue = queue;
        this.rabbitTemplate= rabbitTemplate;
    }
    public void sendMessage(String username, int n,boolean isPrime){
        String message =
                MessageFormat
                        .format(
                                pattern:"Customer:{0}, n: {1}, isPrime:{2}",
                                username, String.valueOf(n),isPrime);
        message="{"+ message +"}";
        rabbitTemplate.convertAndSend(routingKey:"primes",message);
    }
}
