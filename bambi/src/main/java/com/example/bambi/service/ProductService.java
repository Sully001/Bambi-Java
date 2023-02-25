package com.example.bambi.service;

import com.example.bambi.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);

    /* search method to be done
    List<Product> findByKeyword(String keyword);
     */

}