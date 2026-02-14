package com.sliit.product_service;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found."));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updateProduct){
        Product existingProduct = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found to update"));
        existingProduct.setName(updateProduct.getName());
        existingProduct.setPrice(updateProduct.getPrice());

        return productRepository.save(existingProduct);
    }
}


