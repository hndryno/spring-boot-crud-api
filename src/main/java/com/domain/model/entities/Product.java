package com.domain.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tbl_product")
public class Product implements Serializable {

    // private static final long SerialversionUID = 1L;

    @Id //dibuat jadi primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //untuk membuat primary keynya jadi auto increment
    private Long id;

    @NotEmpty(message = "Nama tidak boleh kosong")
    @Column(name="nama_product", length = 100) //ini untuk ganti nama produk
    private String nama; //yang menarik adalah jika String di database lain itu bukan varchar maka si jba akan menyesuaikan itu

    @NotEmpty(message = "Deskripsi tidak boleh kosong")
    @Column(name="deskripsi_produk", length = 500) //ini untuk ganti nama deskripsi produk
    private String description;

    private double price;

    //setter getter dan constructor wajib dibuat, karena belum pakai Lombok jadi kita buat manual 
    //constructor, cara buatnya klik kanan pilih source action, lalu pilih constructor
    public Product() {
    }

    //setter getters, cara buatnya klik kanan pilih source action, lalu pilih setter getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
}
