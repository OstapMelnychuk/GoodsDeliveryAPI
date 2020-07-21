package com.bestfriends.goodsdeliveryapi.product.exceptions;

public class DataImportException extends ProductException {
    public DataImportException(Long errorMessageCode) {
        super(errorMessageCode);
    }
}
