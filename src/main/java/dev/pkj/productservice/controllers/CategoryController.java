package dev.pkj.productservice.controllers;

import dev.pkj.productservice.dtos.FakeStoreCategoryDto;
import dev.pkj.productservice.models.Category;
import dev.pkj.productservice.models.Product;
import dev.pkj.productservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController

public class CategoryController {

    private CategoryService categoryService;
    private RestTemplate restTemplate;

    public CategoryController(@Qualifier("selfCategoryService") CategoryService categoryService, RestTemplate restTemplate) {
        this.categoryService = categoryService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("products/categories")
    public List<String> getAllCategory() {
        return categoryService.getCategories();

    }

    @GetMapping("products/category/{name}")
    public List<Product> getProductsByCategory(@PathVariable("name") String name) {

        return categoryService.getProductsByCategory(name);
    }

}
