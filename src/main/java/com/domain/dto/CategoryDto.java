package com.domain.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDto {

    @NotEmpty(message = "nama tidak boleh kosong")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
