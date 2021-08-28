package com.domain.model.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="tbl_product")
//ini untuk handle data produnya ditampilkan juga di entity
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class, property = "id"
)
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

    //buat relasi
    @ManyToOne
    private Category category;

    //product berelasi many to many dengan supplier
    //di supplier juga buat relasi many to many dengan product
    @ManyToMany
    @JoinTable(
        name="tbl_product_supplier",
        joinColumns = @JoinColumn(name="product_id"),
        inverseJoinColumns = @JoinColumn(name="supplier_id")
    ) //untuk tabel perantara antara table product dengan tabel supplier
    //nanti akan membentuk kolom relasinya yaitu kolom product id dan kolom supplier id
    //setelah buat disini jangn lupa buat disupplier juga, map bynya
    // @JsonManagedReference //ini utk hanle infinite loop. di suppliernya juga tambahin
    private Set<Supplier> suppliers;

    //setter getter dan constructor wajib dibuat, karena belum pakai Lombok jadi kita buat manual 
    //constructor, cara buatnya klik kanan pilih source action, lalu pilih constructor
    public Product() {
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
}
