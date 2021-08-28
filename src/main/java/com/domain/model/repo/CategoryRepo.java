package com.domain.model.repo;

import com.domain.model.entities.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {
    
}