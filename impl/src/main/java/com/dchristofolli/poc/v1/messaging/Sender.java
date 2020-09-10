package com.dchristofolli.poc.v1.messaging;

import com.dchristofolli.poc.v1.repository.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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

    @Value("${rabbitmq.findAllRoutingKey}")
    private String findAllRoutingKey;

    private final DirectExchange directExchange;

    public UserEntity send(UserEntity userEntity) {
        log.info("Sending entity...");
        rabbitTemplate.convertAndSend(
            exchange,
            routingKey,
            userEntity);
        return userEntity;
    }

    public void sendRequestToFindAll() {
        UserEntity userEntity = UserEntity
            .builder()
            .build();
        rabbitTemplate.convertSendAndReceiveAsType(
            directExchange.getName(),
            findAllRoutingKey,
            userEntity,
            new ParameterizedTypeReference<>() {
            }
        );
    }
}
