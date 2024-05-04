package dev.pkj.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Category extends BaseModel implements Serializable {

    private String name;
//    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST},fetch= FetchType.LAZY)
    @JsonIgnore
    private List<Product> products;

//    public Category(String category) {
//        super();
//    }
}
