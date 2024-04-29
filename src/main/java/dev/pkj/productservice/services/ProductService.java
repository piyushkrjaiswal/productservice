package dev.pkj.productservice.services;

import dev.pkj.productservice.dtos.FakeStoreProductDto;
import dev.pkj.productservice.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId);

    List<Product> getProducts();

    Product createProduct(String title,
                          String description,
                          String category,
                          double price,
                          String image);

    Long deleteProduct(Long productId);

    Product updateProduct(Long productId, String title,
                          String description,
                          String category,
                          double price,
                          String image);


}
