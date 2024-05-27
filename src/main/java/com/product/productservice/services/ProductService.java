package com.product.productservice.services;

import com.product.productservice.exceptions.InvalidProductIdException;
import com.product.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id) throws InvalidProductIdException;

    Product updateProduct(Long id, Product product);

    Product replaceProduct(Long id, Product product);

    void deleteProduct(Long id);

}
