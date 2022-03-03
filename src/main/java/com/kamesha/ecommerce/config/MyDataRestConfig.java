package com.kamesha.ecommerce.config;

import com.kamesha.ecommerce.model.Country;
import com.kamesha.ecommerce.model.Product;
import com.kamesha.ecommerce.model.ProductCategory;
import com.kamesha.ecommerce.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig  implements RepositoryRestConfigurer {


    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;
    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod [] myHttpMethods = {HttpMethod.DELETE,HttpMethod.POST,HttpMethod.PUT};

        // disable http method for product
         disabledHttpMethod(Product.class,config,myHttpMethods);

        // disable http method for ProductCategory
        disabledHttpMethod(ProductCategory.class,config, myHttpMethods);

        // disable http method for Country
        disabledHttpMethod(Country.class,config, myHttpMethods);
        // disable http method for State
        disabledHttpMethod(State.class,config, myHttpMethods);



        //expose id
        exposeIds(config);



        // configure cors mapping
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);

    }

    private void disabledHttpMethod( Class theClass ,RepositoryRestConfiguration config, HttpMethod[] myHttpMethods) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(myHttpMethods))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(myHttpMethods));
    }

    private void exposeIds(RepositoryRestConfiguration config) {

        //get a list from all entity type from entity manger
        // from  javax.persistence
        Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();

        //create array of entity type
        List<Class> entityClasses = new ArrayList<>();

        for(EntityType entityType : entityTypes){
            entityClasses.add(entityType.getJavaType());
        }

       // epose entity id
       Class[] dominTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(dominTypes);


    }
}
