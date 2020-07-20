package com.bestfriends.goodsdeliveryapi.product.exceptions;

import java.util.IllegalFormatException;
import java.util.Optional;
import lombok.Getter;

/**
 * Exception used when there is server error.
 * This type of exception is for developers not for end user.
 * If exception message should be seen by end user, use {@link UserException}.
 */
@Getter
public class ProductException extends RuntimeException {
    private final String errorMessage;
    private final Long errorMessageId;
    private final Object[] args;

    /**
     * This constructor used when there is no {@link ProductException#errorMessageId} and message sets directly.
     *
     * @param errorMessage error message visible by end user.
     *                     message is format string where parameters can be present. E.g. 'Some error text %d, %s'.
     *                     Use {@link String#format(String, Object...)} to insert arguments.
     * @param args         arguments to insert in format string.
     */
    public ProductException(String errorMessage, Object... args) {
        super(Optional.ofNullable(errorMessage)
            .map(m -> {
                try {
                    return String.format(errorMessage, args);
                } catch (IllegalFormatException e) {
                    return m;
                }
            }).orElse(null));

        this.errorMessage = errorMessage;
        this.errorMessageId = null;
        this.args = args;
    }

    /**
     * This constructor is used when there is {@link ProductException#errorMessageId} exists for needed message.
     *
     * @param errorMessageId error code used for fetching message from cache. Fetched message is format string where
     *                       parameters can be present. E.g. 'Some error text %d, %s'.
     *                       Use {@link String#format(String, Object...)} to insert arguments.
     * @param args           arguments to insert in format string.
     */
    public ProductException(long errorMessageId, Object... args) {
        this.errorMessage = null;
        this.errorMessageId = errorMessageId;
        this.args = args;
    }

    /**
     * This constructor is used when you need create copy of other {@link ProductException}.
     *
     * @param cause        {@link ProductException} that needs to be copied.
     * @param errorMessage error message. Is not a format string just plain text.
     */
    public ProductException(ProductException cause, String errorMessage) {
        super(errorMessage);

        this.errorMessage = errorMessage;
        this.errorMessageId = cause.getErrorMessageId();
        this.args = cause.getArgs();
    }
}
