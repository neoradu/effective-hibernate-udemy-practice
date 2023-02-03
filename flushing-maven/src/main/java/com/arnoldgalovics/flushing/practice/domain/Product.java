package com.arnoldgalovics.flushing.practice.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * A very simple representation of a Product that is used for demonstration purposes.
 */
@Entity
@Table(name = "products")
public class Product {
    @Id
    private int id;
    private String name;
    private int stock;

    
    @OneToMany(fetch=FetchType.LAZY, mappedBy = "product")
    //@OneToMany(fetch=FetchType.EAGER, mappedBy = "product")
    private List<ProductReview> reviews = null;

    private Product() {
    }

    public Product(int id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public List<ProductReview> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", reviews=" + reviews +
                '}';
    }
}
