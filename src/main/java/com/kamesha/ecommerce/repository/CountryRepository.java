package com.kamesha.ecommerce.repository;

import com.kamesha.ecommerce.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@RepositoryRestResource(collectionResourceRel = "countries" ,path = "countries")
public interface CountryRepository extends JpaRepository<Country , Integer> {

}
