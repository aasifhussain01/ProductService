package com.product.productservice.exceptions;

import lombok.Getter;

@Getter
public class InvalidProductIdException extends Exception{
    private Long productId;

    public InvalidProductIdException(Long id,String message) {
        super(message);
        this.productId = id;
    }
}
