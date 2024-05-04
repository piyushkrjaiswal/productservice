package dev.pkj.productservice.controllers;

import dev.pkj.productservice.dtos.CreateProductRequestDto;
import dev.pkj.productservice.dtos.FakeStoreProductDto;
import dev.pkj.productservice.models.Product;
import dev.pkj.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
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
    @CachePut(value = "product")
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
    @Cacheable(value = "product")
    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long productId) {
        return productService.getSingleProduct(productId) ;

    }

    @Cacheable(value = "product")
    @GetMapping("/products/{pageSize}/{pageNumber}/{sort}")
    public ResponseEntity getAllProduct(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("sort") String sort) {
        Page<Product> productPage = productService.getProducts(pageSize, pageNumber, sort);
        return ResponseEntity.ok(productPage);

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
