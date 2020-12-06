package ru.geekbrains.mini.market.geekbrainsminimarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Product;
import ru.geekbrains.mini.market.geekbrainsminimarket.exceptions.MarketError;
import ru.geekbrains.mini.market.geekbrainsminimarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.mini.market.geekbrainsminimarket.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id));
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        if(product.getId() != null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "id must be null for new entity"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping
    public Product nodifyProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
