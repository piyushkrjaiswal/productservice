package dev.pkj.productservice.services;

import dev.pkj.productservice.dtos.FakeStoreProductDto;
import dev.pkj.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;
    private FakeStoreProductDto fakeStoreProduct;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate ;

    }
    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProduct =  restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
        );
        return fakeStoreProduct.toProduct();
    }

    @Override
    public Page<Product> getProducts(Integer pageSize, Integer pageNumber, String sort) {

        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> list = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreEachProduct: Arrays.stream(Objects.requireNonNull(response.getBody())).toList()) {
            list.add(fakeStoreEachProduct.toProduct());
        }
        return (Page<Product>) list;
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);

        FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDto, FakeStoreProductDto.class);

        return response.toProduct();
    }

    @Override
    public Long deleteProduct(Long productId) {
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange("https://fakestoreapi.com/products/" + productId,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class);

        return productId;

    }

    @Override
    public Product updateProduct(Long productId, String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(fakeStoreProductDto, headers);


        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange("https://fakestoreapi.com/products/" + productId,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class);

        return response.getBody().toProduct();
    }
}
