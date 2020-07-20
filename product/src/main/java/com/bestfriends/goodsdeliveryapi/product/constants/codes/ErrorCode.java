package com.bestfriends.goodsdeliveryapi.product.constants.codes;

/**
 * Contains codes of application errors. Codes are visible for end users.
 * So, they can inform about error code and problem can be resolved easier.
 */
public enum ErrorCode {
    //  This code used when there is exception which has un-existing message id.
    APPLICATION_MESSAGE_ID_DOESNT_EXIST(1);

    private final Long code;

    ErrorCode(long code) {
        this.code = code;
    }

    public Long value() {
        return code;
    }
}
