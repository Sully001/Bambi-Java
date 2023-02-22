package com.example.bambi.repository;

import com.example.bambi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

    //Query matching name, brand, price, gender and category against SQL db
    @Query("SELECT products FROM Product products WHERE products.productName LIKE %?1% " +
            "or products.productBrand LIKE %?1% or CAST(products.productPrice as string) LIKE %?1% or " +
            "products.productGender LIKE %?1% or products.productCategory LIKE %?1%")
    List<Product> search(String keyword);

    @Query("SELECT products FROM Product products WHERE LOWER(products.productName) LIKE %:keyword% ORDER BY products.productPrice DESC")
    List<Product> findByKeywordSortedByPriceDesc(String keyword);

    @Query("SELECT products FROM Product products ORDER BY products.productPrice DESC")
    List<Product> findAllByOrderByPriceDesc();

    @Query("SELECT products FROM Product products WHERE LOWER(products.productName) LIKE %:keyword% ORDER BY products.productPrice ASC")
    List<Product> findByKeywordSortedByPriceAsc(String keyword);

    @Query("SELECT products FROM Product products ORDER BY products.productPrice ASC")
    List<Product> findAllByOrderByPriceAsc();
}