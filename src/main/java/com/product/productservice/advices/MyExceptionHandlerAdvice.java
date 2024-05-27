package com.product.productservice.advices;

import com.product.productservice.dtos.ExceptionDto;
import com.product.productservice.exceptions.InvalidProductIdException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(1)
public class MyExceptionHandlerAdvice {

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ExceptionDto> handleInvalidProductIdException(InvalidProductIdException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Mine Invalid product id passed,please retry with a valid product id");
        exceptionDto.setProductId(ex.getProductId());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

}
