package com.example.demo;

import com.example.demo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
    @Query("{ 'productName' : { $regex: ?0, $options: 'i' }, 'productPrice' : { $gte: ?1, $lte: ?2 } }")
    List<Product> findFilteredProducts(String name, double minPrice, double maxPrice);
}






