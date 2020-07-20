package com.bestfriends.goodsdeliveryapi.product.exceptions;

import com.bestfriends.goodsdeliveryapi.product.cache.ApplicationMessageCash;
import com.bestfriends.goodsdeliveryapi.product.dto.exception.ExceptionResponse;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
class CustomExceptionHandlerTest {
    @Mock
    private ApplicationMessageCash messageCash;
    @Mock
    private Logger logger;

    private CustomExceptionHandler exceptionHandler;

    @BeforeEach
    public void setUp() {
        exceptionHandler = new CustomExceptionHandler(messageCash);
    }

    @Test
    public void testHandleProductExceptionWhenErrorMessageExists() throws NoSuchFieldException {
        ProductException exception = new ProductException("Test Exception");
        ExceptionResponse expectedExceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Interval server error.", null);

        ExceptionResponse actualExceptionResponse = exceptionHandler.handleProductException(exception).getBody();

        assertEquals(expectedExceptionResponse, actualExceptionResponse);
    }

    @Test
    public void testHandleProductExceptionWhenErrorMessageWithArgExists() throws NoSuchFieldException {
        ProductException exception = new ProductException("Test Exception arg1: %s, arg2: %d", "1", 2);
        ExceptionResponse expectedExceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Interval server error.", null);

        ExceptionResponse actualExceptionResponse = exceptionHandler.handleProductException(exception).getBody();

        assertEquals(expectedExceptionResponse, actualExceptionResponse);
    }
}