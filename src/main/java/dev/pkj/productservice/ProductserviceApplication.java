package dev.pkj.productservice;

import dev.pkj.productservice.controllers.ProductController;
import dev.pkj.productservice.models.Product;
import dev.pkj.productservice.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductserviceApplication {

//    private ProductService productService;
    private ProductController productController;

    public ProductserviceApplication(ProductController productController) {
        this.productController = productController;
//        this.productService = productService;
    }

    public static void main(String[] args) {
//        Product p = new Product()
//        p
        SpringApplication.run(ProductserviceApplication.class, args);
    }



}
