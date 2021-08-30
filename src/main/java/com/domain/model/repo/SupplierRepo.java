package com.domain.model.repo;

import java.util.List;

import com.domain.model.entities.Supplier;

import org.springframework.data.repository.CrudRepository;

public interface SupplierRepo extends CrudRepository<Supplier, Long>{
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-lookup-strategies
    //implementasi derived query
    //mencari supplier berdasarkan email
    Supplier findByEmail(String email);

    List <Supplier> findByNameContains(String name);

    //cari supplier yang yang nama dan emailnya mengandung kata query
    List <Supplier> findByNameContainsOrEmailContains(String name, String email);

    // List<Supplier> findByNameContainsOrderByIdAsc(String name);

}
