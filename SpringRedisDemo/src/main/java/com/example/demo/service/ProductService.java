package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // ✅ Fetch from DB, but cache the result
    @Cacheable(value = "products", key = "#id")
    public Product getProductById(int id) {
        System.out.println("⏳ Fetching from DB...");
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // ✅ Save new product (and clear cache for safety)
    @CacheEvict(value = "products", allEntries = true)
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // ✅ Update product (clear that product from cache)
    @CacheEvict(value = "products", key = "#product.id")
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    // ✅ Delete product (also clear from cache)
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    // ✅ Clear all cache
    @CacheEvict(value = "products", allEntries = true)
    public void clearCache() {
        System.out.println("🧹 Cleared all product cache!");
    }
}
