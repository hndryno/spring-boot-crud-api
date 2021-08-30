package com.domain.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.dto.SupplierDto;
import com.domain.model.entities.Supplier;
import com.domain.services.SupplierService;

import org.modelmapper.ModelMapper;
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
@RequestMapping("/api/suppliers")

public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    //dengan model mapper
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierDto supplierDto, Errors errors){
        
        ResponseData<Supplier> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError err: errors.getAllErrors()){
                responseData.getMessages().add(err.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        //harusnya kan kita ambil dari supplierService karena sekarang supplier servicenya kita pecah jadi object object berupa file DTO tadi, jadi kita ambil melalui file DTOnya / transformasi datanya dulu

        //panggil model mapper lalu buat masukan object supplier dtonya setelah itu masukan class suplliernya
        Supplier supplier = modelMapper.map(supplierDto, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    //tanpa model mapper
    // public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierDto supplierDto, Errors errors){
        
    //     ResponseData<Supplier> responseData = new ResponseData<>();

    //     if(errors.hasErrors()){
    //         for(ObjectError err: errors.getAllErrors()){
    //             responseData.getMessages().add(err.getDefaultMessage());
    //         }
    //         responseData.setStatus(false);
    //         responseData.setPayload(null);
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    //     }
    //     //harusnya kan kita ambil dari supplierService karena sekarang supplier servicenya kita pecah jadi object object berupa file DTO tadi, jadi kita ambil melalui file DTOnya / transformasi datanya dulu

    //     Supplier supplier = new Supplier();
    //     supplier.setName(supplierDto.getName());
    //     supplier.setAddress(supplierDto.getAddress());
    //     supplier.setEmail(supplierDto.getEmail());

    //     responseData.setStatus(true);
    //     responseData.setPayload(supplierService.save(supplier));
    //     return ResponseEntity.ok(responseData);
    // }
    
    @GetMapping
    public Iterable<Supplier> findAll(){
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable("id") Long id){
        return supplierService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierDto supplierDto, Errors errors){
        
        ResponseData<Supplier> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError err: errors.getAllErrors()){
                responseData.getMessages().add(err.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        //harusnya kan kita ambil dari supplierService karena sekarang supplier servicenya kita pecah jadi object object berupa file DTO tadi, jadi kita ambil melalui file DTOnya / transformasi datanya dulu

        //panggil model mapper lalu buat masukan object supplier dtonya setelah itu masukan class suplliernya
        Supplier supplier = modelMapper.map(supplierDto, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        supplierService.removeOne(id);
    }

    @PostMapping("/search/email")
    public Supplier findByEmail(@RequestBody SearchData searchData) {
        return supplierService.findByEmail(searchData.getSearchKey());
    }

    @PostMapping("/search/name")
    public List <Supplier> findByNameContains(@RequestBody SearchData searchData) {
        return supplierService.findByNameContains(searchData.getSearchKey());
    }

    @PostMapping("search/nameoremail")
    public List <Supplier> findByNameContainsOrEmailContains(@RequestBody SearchData searchData){
        return supplierService.findByNameContainsOrEmailContains(searchData.getSearchKey(), searchData.getSearchOtherKey());
    }

    // @PostMapping("search/nameorderbyid")
    // public List <Supplier> findByNameOrderById(@RequestBody SearchData searchData){
    //     return supplierService.findByNameOrderById(searchData.getSearchKey());
    // }
    

}
