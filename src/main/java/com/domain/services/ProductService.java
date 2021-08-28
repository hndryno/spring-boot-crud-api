package com.domain.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.domain.model.entities.Product;
import com.domain.model.entities.Supplier;
import com.domain.model.repo.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//pada aplikasi yang sangat kompleks, ini isinya adalah businiss logic
@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    //di java create sama saja dengan update, dengan sangat kerennya si jda ini tau berdasarkan data yang kita input di product itu
    public Product save(Product product){
        //kenapa kita punya fungsi save? padahal gak ada kita buat fungsi create?
        //itu karena di entyties kita sudah extends ke fungsi crudRepositories, yang bisa bikin kita langsung panggil fungsi crud
        return productRepo.save(product);
    }

    public Product findOne(Long id){
        // return productRepo.findById(id).get();
        //untuk handle kalau datanya tidak ditemukan
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent()){
            return null;
        }
        return product.get();
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public void removeOne(Long id){
        productRepo.deleteById(id);
    }

    //bagaimana jika kita ingin membuat fungsi tambahan (fungsi custom)? tinggal didefenisikan di repo langsung
    public List<Product> findByName(String nama){
        return productRepo.findByNamaContains(nama);
    }

    //fungsi tambahan, add fungsi tambahan kedalam produk
    public void addSuppllier(Supplier supplier, Long productId){
        Product product = findOne(productId);

        if(product == null){
            throw new RuntimeException("Product with ID" + productId+ "tidak ditemukan");
        }

        product.getSuppliers().add(supplier);
        save(product);
    }

}
