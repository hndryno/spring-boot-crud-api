package com.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class SupplierDto {

    //kalau tidak semua field yang akan dikirim dalam satu kali kirim maka buatlah class dto ini
    //disini bisa ditaro langsung validasinya

    @NotEmpty(message = "Nama tidak boleh kosong")
    private String name;

    @NotEmpty(message = "Alamat tidak boleh kosong")
    private String address;

    @NotEmpty(message = "Email tidak boleh kosong")
    @Email(message = "Format email tidak valid")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
