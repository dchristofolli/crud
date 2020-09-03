package com.dchristofolli.poc.v1.messaging;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@AllArgsConstructor
public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    @Value("${rabbitmq.exchangeName}")
    private final String exchangeName;

    @Override
    public void run(String... args) throws Exception {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(
            exchangeName,
            "foo.bar.baz",
            "Hello from RabbitMQ!");
        receiver.getLatch().await(10, TimeUnit.SECONDS);
    }

}
