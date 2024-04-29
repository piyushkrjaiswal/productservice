package dev.pkj.productservice.repositories;

import dev.pkj.productservice.models.Category;
import dev.pkj.productservice.models.Product;
import dev.pkj.productservice.repositories.projections.ProductProjection;
import dev.pkj.productservice.repositories.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p);

    List<Product> findAll();

    Product findByIdIs(Long Id);

    List<Product> findProductByTitle(String title);

    @Query(" Select p from Product p where p.category.name = :name and p.id = :productId")
    Product getTheNameOfProductWithParticularName(@Param("name") String name, @Param("productId") Long productId);

    @Query("select p.title as title, p.id as id from Product p where p.category.id = :categoryId")
    List<ProductProjection> getTitleOfProductOfGivenCategory(@Param("categoryId") Long categoryId);

    void deleteById(Long productId);
}
