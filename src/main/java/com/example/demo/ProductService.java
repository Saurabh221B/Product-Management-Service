package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired ProductRepository productRepository;

    public List<Product> getGetAllProducts(){
        return productRepository.findAll();
    }
    public List<Product> getFilteredProducts(String name,Double minPrice,Double maxPrice){
        List<Product> filteredProducts =  productRepository.findFilteredProducts(
                name != null ? name.toLowerCase() : "",
                minPrice != null ? minPrice : Double.MIN_VALUE,
                maxPrice != null ? maxPrice : Double.MAX_VALUE);

        return filteredProducts;
    }
    public Optional<Product> getProductById(String productId){
        return productRepository.findById(Integer.parseInt(productId));
    }
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    public void deleteProduct(String productId){
        productRepository.deleteById(Integer.parseInt(productId));
    }

    public void updateProduct(Product existingProduct, Product updatedProduct){
        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setProductDescription(updatedProduct.getProductDescription());
        existingProduct.setProductPrice(updatedProduct.getProductPrice());
        existingProduct.setAvailabilityStatus(updatedProduct.isAvailabilityStatus());
        productRepository.save(existingProduct);

    }

    public void updateProductPrice(Product existingProduct,double price){

        existingProduct.setProductPrice(price);
        productRepository.save(existingProduct);

    }






}
