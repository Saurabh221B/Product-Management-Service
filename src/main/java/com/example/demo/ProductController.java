package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        List<Product>getAllProducts=productService.getGetAllProducts();
        return ResponseHandler.generateResponse(HttpStatus.OK,getAllProducts,"success");
    }

    @GetMapping("/product")
    public ResponseEntity<?> getFilteredProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        List<Product> filteredProducts =  productService.getFilteredProducts(name,minPrice,maxPrice);


        return ResponseHandler.generateResponse(HttpStatus.OK,filteredProducts,"success");

    }
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable String productId) {
        Optional<Product> product1 = productService.getProductById(productId);

        if(!product1.isPresent()){

            ErrorResponse err=new ErrorResponse("E10010", Arrays.asList("Product do not Exists in the database with the productId :"+productId));
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,err,"error");
        }
        return ResponseHandler.generateResponse(HttpStatus.OK,product1.get(),"status");

    }
    @PostMapping("/addproduct")
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product, BindingResult bindingResult)	{
        Optional<Product> product1 = productService.getProductById(Integer.toString(product.getProductId()));
        if(product1.isPresent()){

            ErrorResponse err=new ErrorResponse("E10010", Arrays.asList("Product  Already Exists in the database with the productId :"+product.getProductId()));
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,err,"error");
        }

            if (bindingResult.hasErrors()) {
                return requestBodyValidator(bindingResult);
//
            }
        Product savedObject = productService.saveProduct(product);
        return ResponseHandler.generateResponse(HttpStatus.OK,savedObject,"success");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
        Optional<Product> product1 = productService.getProductById(productId);

        if(product1.isEmpty()){
            ErrorResponse err=new ErrorResponse("E10010", Arrays.asList("Product do not Exists in the database with the productId :"+productId));
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,err,"error");
        }

        productService.deleteProduct(productId);
        return ResponseHandler.generateResponse(HttpStatus.OK,"Product deleted successfully","success");

    }

    @PutMapping("/{productId}")
    public ResponseEntity<Object> updateProduct(@PathVariable String productId, @RequestBody @Valid Product updatedProduct,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return requestBodyValidator(bindingResult);
        }
        Optional<Product> existingProductOptional = productService.getProductById(productId);

        if(existingProductOptional.isEmpty()){

            ErrorResponse err=new ErrorResponse("E10010", Arrays.asList("Product do not Exists in the database with the productId :"+productId));
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,err,"error");
        }

        Product existingProduct=existingProductOptional.get();
        productService.updateProduct(existingProduct,updatedProduct);
        return ResponseHandler.generateResponse(HttpStatus.OK,"Product updated successfully","success");

    }

    @PutMapping("price-update/{productId}")
    public ResponseEntity<Object> updateProductPrice(@PathVariable String productId,@RequestParam double price){
        Optional<Product> existingProductOptional = productService.getProductById(productId);

        if(existingProductOptional.isEmpty()){

            ErrorResponse err=new ErrorResponse("E10010", Arrays.asList("Product do not Exists in the database with the productId :"+productId));
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,err,"error");
        }
        Product existingProduct=existingProductOptional.get();
        productService.updateProductPrice(existingProduct,price);
        return ResponseHandler.generateResponse(HttpStatus.OK,"Product price updated successfully","success");
    }

    public ResponseEntity<Object>  requestBodyValidator(BindingResult bindingResult){
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String>errors=new ArrayList<>();
        for (FieldError error : fieldErrors) {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);

            System.out.println("Field: " + fieldName + ", Error: " + errorMessage);
        }
        ErrorResponse err=new ErrorResponse("E10010",errors);
        return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,err,"error");


    }


}
