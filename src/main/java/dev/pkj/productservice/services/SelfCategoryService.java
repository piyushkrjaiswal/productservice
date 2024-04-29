package dev.pkj.productservice.services;

import dev.pkj.productservice.models.Category;
import dev.pkj.productservice.models.Product;
import dev.pkj.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("selfCategoryService")
public class SelfCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public SelfCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<String> getCategories() {
        List<Category> category = categoryRepository.findAll();

        List<String> list = new ArrayList<>();
        for(Category category1: category) list.add(String.valueOf(category1.getName()));

        return list;
    }

    @Override
    public List<Product> getProductsByCategory(String name) {
        Category category = categoryRepository.findByName(name);

        return category.getProducts();
    }
}
