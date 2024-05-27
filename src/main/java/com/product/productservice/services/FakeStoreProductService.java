package com.product.productservice.services;

import com.product.productservice.dtos.FakeStoreProductDTO;
import com.product.productservice.exceptions.InvalidProductIdException;
import com.product.productservice.models.Category;
import com.product.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImageUrl(fakeStoreProductDTO.getImageUrl());

        Category category = new Category();
        category.setTitle(fakeStoreProductDTO.getTitle());
        product.setCategory(category);

        return product;
    }

    @Override
    public Product createProduct(Product product) {
        return new Product();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDTO[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDtos) {
            Product product = convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
        if (fakeStoreProductDTO == null) {
            throw new InvalidProductIdException(id,"Invalid productId passed");
        }
        return convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
