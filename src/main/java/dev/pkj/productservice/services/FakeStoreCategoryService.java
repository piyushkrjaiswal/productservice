package dev.pkj.productservice.services;


import dev.pkj.productservice.dtos.FakeStoreCategoryDto;
import dev.pkj.productservice.dtos.FakeStoreProductDto;
import dev.pkj.productservice.models.Category;
import dev.pkj.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService {

    private RestTemplate restTemplate;
    private FakeStoreCategoryDto fakeStoreCategory;

    public FakeStoreCategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> getCategories() {
        String[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                String[].class
        );

        return this.createCategoryFromArrayList(response);
    }

    public List<Product> getProductsByCategory(String name) {
        FakeStoreProductDto[] productList = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + name,
                FakeStoreProductDto[].class);
        List<Product> list = new ArrayList<>();
        assert productList != null;
        for (FakeStoreProductDto product: Arrays.stream(productList).toList()) list.add(product.toProduct());

        return list;

    }

    private List<String> createCategoryFromArrayList(String[] categories) {
        List<String> list = new ArrayList<>();
        for(String category: categories) list.add(category);
//        (new Category(category));
        return list;


    }


}
