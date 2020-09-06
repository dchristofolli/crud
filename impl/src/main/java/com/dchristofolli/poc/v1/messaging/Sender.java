package com.dchristofolli.poc.v1.messaging;

import com.dchristofolli.poc.v1.repository.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class Sender {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchangeName}")
    private final String exchange;

    @Value("${rabbitmq.routingKey}")
    private final String routingKey;

    public UserEntity send(UserEntity userEntity) {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(
            exchange,
            routingKey,
            userEntity);
        return userEntity;
    }
}
