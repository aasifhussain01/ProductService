package com.product.productservice.advices;
import com.product.productservice.dtos.ExceptionDto;
import com.product.productservice.exceptions.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ExceptionDto> handleInvalidProductIdException(InvalidProductIdException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Invalid product id passed,please retry with a valid product id");
        exceptionDto.setProductId(ex.getProductId());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

}
