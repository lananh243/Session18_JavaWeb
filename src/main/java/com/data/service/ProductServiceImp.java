package com.data.service;

import com.data.entity.Product;
import com.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepo;

    @Override
    public boolean addProduct(Product product) {
        return productRepo.addProduct(product);
    }

    @Override
    public List<Product> findAllProduct(int page, int size) {
        return productRepo.findAllProduct(page, size);
    }

    @Override
    public long countProducts() {
        return productRepo.countProducts();
    }

    @Override
    public List<Product> filterProductByPrice(double minPrice, double maxPrice, int page, int size) {
        return productRepo.filterProductByPrice(minPrice, maxPrice, page, size);
    }

    @Override
    public long countFilteredProducts(double minPrice, double maxPrice) {
        return productRepo.countFilteredProducts(minPrice, maxPrice);
    }
}
