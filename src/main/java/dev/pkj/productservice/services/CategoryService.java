package dev.pkj.productservice.services;

import dev.pkj.productservice.models.Category;
import dev.pkj.productservice.models.Product;

import java.util.List;

public interface CategoryService {

  List<String> getCategories();

  List<Product> getProductsByCategory(String name);
}
