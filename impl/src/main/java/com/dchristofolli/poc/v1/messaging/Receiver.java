package com.dchristofolli.poc.v1.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
public class Receiver {
  private final CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(String message) {
    log.info("Received <{}>", message);
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}
