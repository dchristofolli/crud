package com.dchristofolli.poc.v1.messaging;

import com.dchristofolli.poc.v1.repository.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {
    @RabbitListener(queues = "${rabbitmq.responseQueueName}")
    @SendTo("${rabbitmq.responseQueueName}")
    public void receiveMessage(final UserEntity userEntity) {
        log.info("Received callback message: {}", userEntity);
    }
}
