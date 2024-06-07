package com.product.productservice.repositories;

import com.product.productservice.models.Product;
import com.product.productservice.repositories.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);

    @Override
    void deleteById(Long id);

    @Query("Select p from Product p where p.id = :pid ")
    Product myCustomHqlQuery(@Param("pid") Long id);

    @Query(value = "SELECT * from product p where p.id = :pid",nativeQuery = true)
    Product myCustomSqlQuery(@Param("pid") Long id);

    @Query("Select p.id as id ,p.title as title from Product p where p.id = :pid")
    ProductWithIdAndTitle getProductWithIdAndTitle(@Param("pid") Long id);

    @Query(value = "Select p.id as id,p.title as title from product p where p.id = :pid",nativeQuery = true)
    ProductWithIdAndTitle getProductWithIdAndTitleSQL(@Param("pid") Long id);

    @Query(value = "Select * from product p where p.id = 2",nativeQuery = true)
    Product getProductWithValue();

}
