package dev.pkj.productservice.controllers;

import dev.pkj.productservice.dtos.CreateProductRequestDto;
import dev.pkj.productservice.dtos.FakeStoreProductDto;
import dev.pkj.productservice.models.Product;
import dev.pkj.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate;

    public ProductController(@Qualifier("selfProductService") ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto request) {
        return productService.createProduct(
                request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage()
        );

    }

    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long productId) {
        return productService.getSingleProduct(productId) ;

    }

    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return productService.getProducts();

    }

    @PatchMapping("/products/{id}")
    public Product updateProduct(@RequestBody CreateProductRequestDto request, @PathVariable("id") Long productId) {
        return productService.updateProduct(productId, request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage()
        );

    }

    @DeleteMapping("/products/{id}")
    public Long deleteProduct(@PathVariable("id") Long productId) {
        return productService.deleteProduct(productId);
    }
}
