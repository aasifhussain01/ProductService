package com.product.productservice.services;

import com.product.productservice.exceptions.InvalidProductIdException;
import com.product.productservice.models.Category;
import com.product.productservice.models.Product;
import com.product.productservice.repositories.CategoryRepository;
import com.product.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SelfProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();
//        Don't need to do as we have defined cascading
//        if (category.getId() == null) {
//            //first save the category in the DB
//            Category savedCategory = categoryRepository.save(category);
//            product.setCategory(savedCategory);
//        }

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new InvalidProductIdException(id,"Invalid Product ID");
        }

        return product.get();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Invalid Product ID");
        }
        if(product == null) throw new RuntimeException("Invalid Input");
        Product currentProduct = optionalProduct.get();
        if(product.getTitle() !=null){
            currentProduct.setTitle(product.getTitle());
        }
        if(product.getDescription() !=null){
            currentProduct.setDescription(product.getDescription());
        }
       return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
