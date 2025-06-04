package com.data.repository;

import com.data.entity.Product;

import java.util.List;

public interface ProductRepository {
    boolean addProduct(Product product);
    List<Product> findAllProduct(int page, int size);
    long countProducts();
    List<Product> filterProductByPrice(double minPrice,  double maxPrice, int page, int size);
    long countFilteredProducts(double minPrice, double maxPrice);
}
