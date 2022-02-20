package com.kamesha.ecommerce.repository;

import com.kamesha.ecommerce.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

// name of jason entry  collectionResourceRel = "productCategory"
@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "productCategory" ,path = "product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
