package com.domain.controllers;

import java.util.List;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.model.entities.Product;
import com.domain.model.entities.Supplier;
import com.domain.model.repo.ProductRepo;
import com.domain.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")

public class ProductController {
    
    //controller akan memanggil service, service akan memanggil repository
    @Autowired
    private ProductService productService;

    @PostMapping
    //sesudah pakai validator
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError err: errors.getAllErrors()){
                // kalau ada error kita tampilkan errornya
                responseData.getMessages().add(err.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    //sebelum pakai validator
    // public Product create(@Valid @RequestBody Product product, Errors errors) {
    //     if(errors.hasErrors()){
    //         for(ObjectError err: errors.getAllErrors()){
    //             // kalau ada error kita cetak diconsole
    //             System.err.println(err.getDefaultMessage());
    //         }
    //         throw new RuntimeException("Error validasi");
    //     }
    //     return productService.save(product);
    // }

    @GetMapping
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id){
        return productService.findOne(id);
    }

    //put mapping disini tidak pakai id, dia melakukan fungsi upsert
    //sesudah validasi
    @PutMapping
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

       if(errors.hasErrors()){
           for(ObjectError err: errors.getAllErrors()){
               // kalau ada error kita tampilkan errornya
               responseData.getMessages().add(err.getDefaultMessage());
           }
           responseData.setStatus(false);
           responseData.setPayload(null);
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
       }
       responseData.setStatus(true);
       responseData.setPayload(productService.save(product));
       return ResponseEntity.ok(responseData);
   }
   //sebelum validasi
    // public Product update(@RequestBody Product product) {
    //     return productService.save(product);
    // }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        productService.removeOne(id);
    }

    @PostMapping("/{id}")
    public void addSuppllier(@RequestBody Supplier supplier, @PathVariable("id") Long productId){
        productService.addSuppllier((supplier), productId);
    }

    //didapat dari service
    @PostMapping("/search/name")
    public Product getProductByName(@RequestBody SearchData searchData){
        return productService.findProductByNama(searchData.getSearchKey());
    }

    //kalau returnnya array kita pakai list
    @PostMapping("/search/namelike")
    public List<Product> getProductByNamaLike(@RequestBody SearchData searchData){
        // System.out.println();
        return productService.findProductByNamaLike(searchData.getSearchKey());
    }

    @GetMapping("/search/category/{categoryId}")
    public List<Product> getProductByCategoryId(@PathVariable("categoryId") Long categoryId){
        return productService.findProductByCategoryId(categoryId);
    }

    @GetMapping("/search/supplier/{supplierId}")
    public List<Product> getProductBySupplierId(@PathVariable("supplierId") Long supplierId){
        return productService.findByProductSupplier(supplierId);
    }
}
