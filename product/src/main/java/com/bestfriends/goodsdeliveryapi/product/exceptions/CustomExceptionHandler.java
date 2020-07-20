package com.bestfriends.goodsdeliveryapi.product.exceptions;

import com.bestfriends.goodsdeliveryapi.product.cache.ApplicationMessageCash;
import com.bestfriends.goodsdeliveryapi.product.cache.entities.CachedApplicationMessage;
import com.bestfriends.goodsdeliveryapi.product.constants.codes.ErrorCode;
import com.bestfriends.goodsdeliveryapi.product.dto.exception.ExceptionResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.IllegalFormatException;
import java.util.Optional;

import static com.bestfriends.goodsdeliveryapi.product.constants.codes.ErrorCode.APPLICATION_MESSAGE_ID_DOESNT_EXIST;

@ControllerAdvice
@Log4j2
public class CustomExceptionHandler {
    private final ApplicationMessageCash messageCash;

    @Autowired
    public CustomExceptionHandler(ApplicationMessageCash messageCash) {
        this.messageCash = messageCash;
    }

    @ExceptionHandler(value = ProductException.class)
    public ResponseEntity<ExceptionResponse> handleProductException(ProductException e) {
        Optional<String> compiledMessage = compileMessage(e);

        if (compiledMessage.isPresent()) {
            e = new ProductException(e, compiledMessage.get());
        }

        log.error(e);

        if (compiledMessage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Interval server error.", APPLICATION_MESSAGE_ID_DOESNT_EXIST.value()));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Interval server error.",
                        null));
    }

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ExceptionResponse> handleUserException(UserException e) {
        Optional<String> compiledMessage = compileMessage(e);

        if (compiledMessage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Interval server error.", APPLICATION_MESSAGE_ID_DOESNT_EXIST.value()));
        }

        return ResponseEntity.status(e.getHttpStatus())
                .body(new ExceptionResponse(e.getHttpStatus().value(), compiledMessage.get(),
                        Optional.ofNullable(e.getErrorCode())
                                .map(ErrorCode::value)
                                .orElse(null)));
    }

    private Optional<String> compileMessage(ProductException e) {
        Optional<String> compiledMessage;

        if (e.getErrorMessage() != null) {
            compiledMessage = Optional.ofNullable(e.getErrorMessage());
        } else {
            compiledMessage = messageCash.findById(e.getErrorMessageId())
                    .map(CachedApplicationMessage::getMessage);
        }

        return compiledMessage.map(m -> {
            try {
                return String.format(m, e.getArgs());
            } catch (IllegalFormatException ex) {
                return m;
            }
        });
    }
}
