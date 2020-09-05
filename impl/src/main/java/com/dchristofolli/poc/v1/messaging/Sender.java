package com.dchristofolli.poc.v1.messaging;

import com.dchristofolli.poc.v1.repository.UserEntity;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@AllArgsConstructor
public class Sender {
    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    @Value("${rabbitmq.exchangeName}")
    private final String exchangeName;

    @Value("${rabbitmq.routingKey}")
    private final String routingKey;

    private final Gson gson;

    public UserEntity send(UserEntity entity) {
        rabbitTemplate.convertAndSend(
            exchangeName,
            routingKey,
            gson.toJson(entity));
        try {
            receiver.getLatch().await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return entity;
    }

}
