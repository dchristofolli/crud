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

//    @Test
//    public void handleApiException() {
//        ConflictException e = new ConflictException("Conflict", HttpStatus.CONFLICT);
//        ErrorModel errorModel = handler.handleDuplicateKeyException(e);
//        Assert.assertEquals("Conflict", errorModel.getMessage());
//    }

    @Test
    public void handleException() {
        Exception e = new Exception();
        ErrorModel errorModel = handler.handleException(e);
        Assert.assertEquals("Unexpected Error", errorModel.getMessage());
    }

//    @Test
//    public void handleValidationExceptions() {
//        MethodParameter parameter = new MethodParameter(method, 1);
//        MethodArgumentNotValidException e = null;
//        BindingResult result = e.getBindingResult();
//
//        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(parameter, result);
//        ErrorModel errorModel = handler.handleValidationExceptions(exception);
//        Assert.assertEquals("Bad request", errorModel.getMessage());
//    }
}