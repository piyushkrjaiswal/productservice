package dev.pkj.productservice.repositories.projections;

public interface ProductProjection {
    Long getId();
    String getTitle();

    String getDescription();
}
