package com.domain.controllers;

import javax.validation.Valid;

import com.domain.dto.CategoryDto;
import com.domain.dto.ResponseData;
import com.domain.model.entities.Category;
import com.domain.services.CategoryService;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/categories")


public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryDto categoryDto, Errors errors){
        
        ResponseData<Category> responseData = new ResponseData<>();

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
        Category category = modelMapper.map(categoryDto, Category.class);

        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable("id") Long id){
        return categoryService.findOne(id);
    }

    @GetMapping
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryDto categoryDto, Errors errors){
        
        ResponseData<Category> responseData = new ResponseData<>();

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
        Category category = modelMapper.map(categoryDto, Category.class);

        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        categoryService.removeOne(id);
    }

}