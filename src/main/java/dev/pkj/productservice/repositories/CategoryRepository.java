package dev.pkj.productservice.repositories;

import dev.pkj.productservice.models.Category;
import dev.pkj.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    Category save(Category c);

    List<Category> findAll();


}
