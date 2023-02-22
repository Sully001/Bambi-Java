package com.example.bambi.service.impl;

import com.example.bambi.entity.Product;
import com.example.bambi.repository.ProductRepository;
import com.example.bambi.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts(String keyword) {
        if(keyword != null){
            return productRepository.search(keyword);
        }
            return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProductsSortedByPriceDesc(String keyword, String sort) {
        if (keyword != null) {
            return productRepository.findByKeywordSortedByPriceDesc(keyword);
        } else {
            if (sort != null && sort.equals("desc")) {
                return productRepository.findAllByOrderByPriceDesc();
            } else {
                return productRepository.findAllByOrderByPriceAsc();
            }
        }
    }

    @Override
    public List<Product> getAllProductsSortedByPriceAsc(String keyword, String sort) {
        if (keyword != null) {
            return productRepository.findByKeywordSortedByPriceAsc(keyword);
        } else {
            if (sort != null && sort.equals("desc")) {
                return productRepository.findAllByOrderByPriceDesc();
            } else {
                return productRepository.findAllByOrderByPriceAsc();
            }
        }
    }


}
