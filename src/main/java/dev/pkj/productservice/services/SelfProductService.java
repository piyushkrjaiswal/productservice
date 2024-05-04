package dev.pkj.productservice.services;

import dev.pkj.productservice.dtos.FakeStoreProductDto;
import dev.pkj.productservice.models.Category;
import dev.pkj.productservice.models.Product;
import dev.pkj.productservice.repositories.CategoryRepository;
import dev.pkj.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Objects;

@Service("selfProductService")

public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        return productRepository.findByIdIs(productId);
    }

    @Override
    public Page<Product> getProducts(Integer pageSize, Integer pageNumber, String sort) {
//        return productRepository.findAll();
//        return null;
        Pageable pageable = null;
        if(sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return productRepository.findAll(pageable);

    }

    @Override
    public Product createProduct(String title, String description, String category, double price, String image) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);

        Category category1 = categoryRepository.findByName(category);
        if (category1 == null) {
            category1 = new Category();
            category1.setName(category);
//            category1 = categoryRepository.save(category1);
        }

        product.setCategory(category1);
        Product saveProduct = productRepository.save(product);

        return saveProduct;
    }

    @Override
    public Long deleteProduct(Long productId) {
        if(productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        }

        return productId;
    }

    @Override
    public Product updateProduct(Long productId, String title, String description, String category, double price, String image) {
        Product product = productRepository.findByIdIs(productId);
        if (title != null) {
           product.setTitle(title);
        }
        if (description != null) {
            product.setDescription(description);
        }
        if(category != null) {
            Category category1 = categoryRepository.findByName(category);
            if(category1 == null) {
                category1 = new Category();
                category1.setName(category);
                product.setCategory(category1);
            }
        }
        if(!Double.isNaN(price)) {
            product.setPrice(price);
        }
        if(image != null) {
            product.setImageUrl(image);
        }

        Product product1 = productRepository.save(product);

        return product1;
    }
}
