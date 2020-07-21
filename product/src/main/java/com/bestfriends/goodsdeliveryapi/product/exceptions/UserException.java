package com.bestfriends.goodsdeliveryapi.product.exceptions;

import com.bestfriends.goodsdeliveryapi.product.constants.codes.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This exception used when there is error that user should see.
 * E.g. user entered wrong data and should see cause of error.
 * If there is server error use {@link ProductException}.
 */
@Getter
public class UserException extends ProductException {
    private final HttpStatus httpStatus;
    private final ErrorCode errorCode;

    /**
     * All args constructor.
     *
     * @param errorMessageId error code used for fetching message from cache. Fetched message is format string where
     *                       parameters can be present. E.g. 'Some error text %d, %s'.
     *                       Use {@link String#format(String, Object...)} to insert arguments.
     * @param httpStatus     {@link HttpStatus} that will be returned to client.
     * @param errorCode      {@link ErrorCode}.
     * @param args           arguments to insert in format string.
     */
    public UserException(Long errorMessageId, HttpStatus httpStatus, ErrorCode errorCode, Object... args) {
        super(errorMessageId, args);

        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    /**
     * All args constructor.
     *
     * @param errorMessage error message visible by end user.
     *                     message is format string where parameters can be present. E.g. 'Some error text %d, %s'.
     *                     Use {@link String#format(String, Object...)} to insert arguments.
     * @param httpStatus   {@link HttpStatus} that will be returned to client.
     * @param errorCode    {@link ErrorCode}.
     * @param args         arguments to insert in format string.
     */
    public UserException(String errorMessage, HttpStatus httpStatus, ErrorCode errorCode, Object... args) {
        super(errorMessage, args);

        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}
