package ru.geekbrains.mini.market.geekbrainsminimarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Category;
import ru.geekbrains.mini.market.geekbrainsminimarket.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getOneById(Long id) {
        return categoryRepository.findById(id);
    }
}
