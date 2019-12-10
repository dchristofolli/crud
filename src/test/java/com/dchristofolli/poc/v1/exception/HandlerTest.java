package com.dchristofolli.poc.v1.exception;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class HandlerTest {
    @InjectMocks
    Handler handler;

    @Test
    public void apiExceptionValidator() {
        ApiException e = new ApiException("Not found", HttpStatus.NOT_FOUND);
        ErrorModel errorModel = handler.apiExceptionValidator(e);
        Assert.assertEquals("Not found", errorModel.getMessage());
    }

    @Test
    public void handleException() {
        Exception e = new Exception();
        ErrorModel errorModel = handler.handleException(e);
        Assert.assertEquals("Unexpected Error", errorModel.getMessage());
    }
}