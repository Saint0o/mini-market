package ru.geekbrains.mini.market.geekbrainsminimarket.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mini.market.geekbrainsminimarket.dto.CategoryDto;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Category;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Product;
import ru.geekbrains.mini.market.geekbrainsminimarket.exceptions.MarketError;
import ru.geekbrains.mini.market.geekbrainsminimarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.mini.market.geekbrainsminimarket.service.CategoryService;
import ru.geekbrains.mini.market.geekbrainsminimarket.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@Api("Set of endpoints for products")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        Category c = categoryService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find category with id: " + id));
        return new CategoryDto(c);
    }


}
