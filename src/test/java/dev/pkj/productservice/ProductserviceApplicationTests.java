package dev.pkj.productservice;

import dev.pkj.productservice.repositories.ProductRepository;
import dev.pkj.productservice.repositories.projections.ProductProjection;
import dev.pkj.productservice.repositories.projections.ProductWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootTest
class ProductserviceApplicationTests {

    @Autowired
    private ProductRepository productRepository;
    @Test
    void contextLoads() {
    }

    @Test
    void testingQueries(ApplicationContext context) {
//        List<ProductProjection> p = productRepository.findProductByTitle("Piyush");


        List<ProductProjection> pros = productRepository.getTitleOfProductOfGivenCategory(
                1L
        );
        System.out.println(pros.get(0).getId());
        System.out.println(pros.get(0).getTitle());

    }



}
