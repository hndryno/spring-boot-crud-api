package com.domain.model.repo;

import java.util.List;

import javax.websocket.server.PathParam;

import com.domain.model.entities.Product;
import com.domain.model.entities.Supplier;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//tanpa kita input apa apa, si product ini akan memiliki fungsi untuk crud, kereen bgt woi haha
public interface ProductRepo extends CrudRepository<Product, Long> {
    //ini adalah function custom yang gunanya untuk pencarian
    List<Product> findByNamaContains(String nama);

    //custom query
    //disini querynya menggunakan jpaql, kalau mysql itu kita selectnya ke table, tapi kalau jpaql itu kita select ke class entitynya
    //habis dari sini kita buatkan servicenya untuk memanggil ini
    @Query("SELECT p FROM Product p WHERE p.nama = :nama")
    public Product findProductByNama(@PathParam("nama") String nama);

    //kalau ini pakai like
    @Query("SELECT p FROM Product p WHERE p.nama LIKE :nama")
    public List<Product> findProductByNamaLike(@PathParam("nama") String nama);

    //mencari product berdasarkan relasi product dengan kategory
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    public List<Product> findProductByCategoryId(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    public List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);
}
