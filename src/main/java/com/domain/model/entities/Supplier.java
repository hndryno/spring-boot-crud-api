package com.domain.model.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="suppliers")
//ini untuk handle data product ditampilkan juga di entity
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class, property = "id"
)
public class Supplier implements Serializable { //utk memastikan bahwa kelas supplier kita bisa di serialize
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 100, nullable = false, unique = true)
    private String name;
    @Column(length = 150, nullable = false)
    private String address;
    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "suppliers")
    // @JsonBackReference//ini utk hanle infinite loop. di productnya juga tambahin
    private Set<Product> products; // jadi kita bisa tau supplier ini productnya apa saja.

    public Long getId() {
        return Id;
    }
    public Set<Product> getProducts() {
        return products;
    }
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    public void setId(Long id) {
        Id = id;
    }
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