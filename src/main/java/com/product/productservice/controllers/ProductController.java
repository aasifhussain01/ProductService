package com.product.productservice.controllers;

import com.product.productservice.exceptions.InvalidProductIdException;
import com.product.productservice.models.Product;
import com.product.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws InvalidProductIdException {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        product = productService.createProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,@RequestBody Product product) {
        Product product1 = productService.updateProduct(id, product);
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id,@RequestBody Product product) {
        Product product1 = productService.replaceProduct(id, product);
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        return;
    }

//     First priority is under controller then controllerAdice with @Order(1) lower is highest priority
//    @ExceptionHandler(InvalidProductIdException.class)
//    public ResponseEntity<ExceptionDto> handleInvalidProductIdException(InvalidProductIdException ex) {
//        ExceptionDto exceptionDto = new ExceptionDto();
//        exceptionDto.setMessage("My Invalid product id passed,please retry with a valid product id");
//        exceptionDto.setProductId(ex.getProductId());
//        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
//    }
}
