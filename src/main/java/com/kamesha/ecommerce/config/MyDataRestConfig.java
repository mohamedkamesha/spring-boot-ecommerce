package com.kamesha.ecommerce.config;

import com.kamesha.ecommerce.model.Product;
import com.kamesha.ecommerce.model.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig  implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod [] myHttpMethods = {HttpMethod.DELETE,HttpMethod.POST,HttpMethod.PUT};

        // disable http method for product
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(myHttpMethods))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(myHttpMethods));

        // disable http method for ProductCategory
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(myHttpMethods))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(myHttpMethods));
    }
}
