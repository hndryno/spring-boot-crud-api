package com.domain.model.repo;

import java.util.List;

import com.domain.model.entities.Product;

import org.springframework.data.repository.CrudRepository;

//tanpa kita input apa apa, si product ini akan memiliki fungsi untuk crud, kereen bgt woi haha
public interface ProductRepo extends CrudRepository<Product, Long> {
    //ini adalah function custom yang gunanya untuk pencarian
    List<Product> findByNamaContains(String nama);

}
