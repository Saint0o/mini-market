package ru.geekbrains.mini.market.geekbrainsminimarket.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mini.market.geekbrainsminimarket.dto.ProductDto;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Product;
import ru.geekbrains.mini.market.geekbrainsminimarket.exceptions.MarketError;
import ru.geekbrains.mini.market.geekbrainsminimarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.mini.market.geekbrainsminimarket.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Api("Set of endpoints for products")
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
    @ApiOperation("Returns a specific product by their identifier. 404 uf does not exist.")
    public ProductDto getProductById(@ApiParam("Id of the product to be obtained. Cannot be empty.") @PathVariable Long id) {
        Product p = productService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id));
        return new ProductDto(p);
    }

    @PostMapping
    @ApiOperation("Creates a new product. If id != null, then throw bad request response")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        if(product.getId() != null) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "id must be null for new entity"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation("Modify product")
    public Product modifyProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping
    @ApiOperation("delete product")
    public void deleteById(@ApiParam("Id of the product to be obtained.") @PathVariable Long id) {
        productService.deleteById(id);
    }
}
