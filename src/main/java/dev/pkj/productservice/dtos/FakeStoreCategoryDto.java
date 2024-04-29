package dev.pkj.productservice.dtos;

import dev.pkj.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreCategoryDto {
//    private Long id;
    private String name;

    public Category toCategory() {
        Category category = new Category();
        category.setName(name);

        return category;
    }
}
