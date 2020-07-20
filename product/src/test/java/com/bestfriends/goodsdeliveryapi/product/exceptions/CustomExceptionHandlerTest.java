package com.bestfriends.goodsdeliveryapi.product.exceptions;

import static com.bestfriends.goodsdeliveryapi.product.constants.codes.ErrorCode.APPLICATION_MESSAGE_ID_DOESNT_EXIST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bestfriends.goodsdeliveryapi.product.cache.ApplicationMessageCash;
import com.bestfriends.goodsdeliveryapi.product.cache.entities.CachedApplicationMessage;
import com.bestfriends.goodsdeliveryapi.product.dto.exception.ExceptionResponse;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith( {MockitoExtension.class})
class CustomExceptionHandlerTest {
    @Mock
    private ApplicationMessageCash messageCash;

    private CustomExceptionHandler exceptionHandler;

    @BeforeEach
    public void setUp() {
        exceptionHandler = new CustomExceptionHandler(messageCash);
    }

    @ParameterizedTest
    @MethodSource("provideProductExceptions")
    public void testHandleProductException(ProductException exception, ExceptionResponse exceptionResponse) {
        ExceptionResponse actualExceptionResponse = exceptionHandler.handleProductException(exception).getBody();

        assertEquals(exceptionResponse, actualExceptionResponse);
    }

    @Test
    public void testHandleProductExceptionWhenExistingErrorMessageIdPassed() {
        ProductException exception = new ProductException(1L);
        ExceptionResponse expectedExceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Interval server error.", null);

        Mockito.when(messageCash.findById(1L))
            .thenReturn(Optional.of(new CachedApplicationMessage(1L, "Test message")));

        ExceptionResponse actualExceptionResponse = exceptionHandler.handleProductException(exception).getBody();
        assertEquals(expectedExceptionResponse, actualExceptionResponse);
    }

    @Test
    public void testHandleProductExceptionWhenNotExistingErrorMessageIdPassed() {
        ProductException exception = new ProductException(1L);
        ExceptionResponse expectedExceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Interval server error.", APPLICATION_MESSAGE_ID_DOESNT_EXIST.value());

        Mockito.when(messageCash.findById(1L))
            .thenReturn(Optional.empty());

        ExceptionResponse actualExceptionResponse = exceptionHandler.handleProductException(exception).getBody();
        assertEquals(expectedExceptionResponse, actualExceptionResponse);
    }

    @ParameterizedTest
    @MethodSource("provideUserExceptions")
    public void testHandleUserException(UserException exception, ExceptionResponse exceptionResponse) {
        ExceptionResponse actualExceptionResponse = exceptionHandler.handleUserException(exception).getBody();

        assertEquals(exceptionResponse, actualExceptionResponse);
    }

    @Test
    public void testHandleUserExceptionWhenExistingErrorMessageIdPassed() {
        UserException exception = new UserException(1L, HttpStatus.NOT_FOUND, APPLICATION_MESSAGE_ID_DOESNT_EXIST);
        ExceptionResponse expectedExceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(),
            "Test message", APPLICATION_MESSAGE_ID_DOESNT_EXIST.value());

        Mockito.when(messageCash.findById(1L))
            .thenReturn(Optional.of(new CachedApplicationMessage(1L, "Test message")));

        ExceptionResponse actualExceptionResponse = exceptionHandler.handleUserException(exception).getBody();
        assertEquals(expectedExceptionResponse, actualExceptionResponse);
    }

    @Test
    public void testHandleUserExceptionWhenNotExistingErrorMessageIdPassed() {
        UserException exception = new UserException(1L, HttpStatus.NOT_FOUND, APPLICATION_MESSAGE_ID_DOESNT_EXIST);
        ExceptionResponse expectedExceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Interval server error.", APPLICATION_MESSAGE_ID_DOESNT_EXIST.value());

        Mockito.when(messageCash.findById(1L))
            .thenReturn(Optional.empty());

        ExceptionResponse actualExceptionResponse = exceptionHandler.handleUserException(exception).getBody();
        assertEquals(expectedExceptionResponse, actualExceptionResponse);
    }

    private static Stream<Arguments> provideProductExceptions() {
        return Stream.of(
            Arguments.of(new ProductException("Test Exception"), new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Interval server error.", null)),

            Arguments.of(new ProductException("Test Exception arg1: %s, arg2: %d", "1", 2), new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Interval server error.", null)),

            Arguments.of(new ProductException("Wrong format string %d", "string"), new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Interval server error.", null))
        );
    }

    private static Stream<Arguments> provideUserExceptions() {
        return Stream.of(
            Arguments.of(new UserException("Test Exception", HttpStatus.NOT_FOUND, APPLICATION_MESSAGE_ID_DOESNT_EXIST),
                new ExceptionResponse(HttpStatus.NOT_FOUND.value(),
                    "Test Exception", APPLICATION_MESSAGE_ID_DOESNT_EXIST.value())),

            Arguments.of(new UserException("Test Exception arg1: %s, arg2: %d", HttpStatus.BAD_REQUEST,
                    APPLICATION_MESSAGE_ID_DOESNT_EXIST, "1", 2),
                new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                    String.format("Test Exception arg1: %s, arg2: %d", "1", 2),
                    APPLICATION_MESSAGE_ID_DOESNT_EXIST.value())),

            Arguments.of(new UserException("Test Exception arg1: %s, arg2: %d", HttpStatus.BAD_REQUEST,
                    APPLICATION_MESSAGE_ID_DOESNT_EXIST, 1, "2"),
                new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                    "Test Exception arg1: %s, arg2: %d",
                    APPLICATION_MESSAGE_ID_DOESNT_EXIST.value()))
        );
    }
}