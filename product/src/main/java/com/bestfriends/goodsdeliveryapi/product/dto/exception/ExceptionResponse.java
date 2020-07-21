package com.bestfriends.goodsdeliveryapi.product.dto.exception;

import lombok.Data;

@Data
public class ExceptionResponse {
    private Integer status;

    private String message;

    private Long errorCode;

    public ExceptionResponse(int status, String message, Long errorCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }
}
