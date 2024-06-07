package com.product.productservice;

import com.product.productservice.models.Category;
import com.product.productservice.models.Product;
import com.product.productservice.repositories.CategoryRepository;
import com.product.productservice.repositories.ProductRepository;
import com.product.productservice.repositories.projections.ProductWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testQueries() {
//        List<ProductWithIdAndTitle> products = productRepository.someRandomQuery();
//
//        for (ProductWithIdAndTitle product : products) {
//            System.out.println(product.getId());
//            System.out.println(product.getTitle());
//        }

        ProductWithIdAndTitle product = productRepository.getProductWithIdAndTitle(3L);
        System.out.println(product.getId());
        System.out.println(product.getTitle());

        ProductWithIdAndTitle product1 = productRepository.getProductWithIdAndTitleSQL(1L);

        Optional<Product> productOptional = productRepository.findById(2L);
        Product product2 = null;
        if (productOptional.isPresent()) {
            product2 = productOptional.get();
        }

        Optional<Category> optionalCategory = categoryRepository.findById(2L);

        Category category = optionalCategory.get();

        Product product3 = productRepository.getProductWithValue();
        System.out.println("Fetched Category");

        Optional<Product> productOptional1 = productRepository.findById(2L);
        Product product4 = null;
        if (productOptional.isPresent()) {
            product4 = productOptional1.get();
        }
        List<Product> products = category.getProducts();
        System.out.println("DEBUG");
    }

}
