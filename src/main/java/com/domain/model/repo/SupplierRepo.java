package com.domain.model.repo;

import com.domain.model.entities.Supplier;

import org.springframework.data.repository.CrudRepository;

public interface SupplierRepo extends CrudRepository<Supplier, Long>{
    
}
