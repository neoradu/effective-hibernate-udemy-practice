package com.arnoldgalovics.concurrencycontrolpractice.domain.pessimistic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicUpdate;

/**
 * A very simple representation of a Product that is used for demonstration purposes.
 */
@Entity
@Table(name = "pessimistic_product")
@DynamicUpdate
public class PessimisticProduct {
    @Id
    private int id;
    private String name;
    private int stock;
    @Version
    @Column(columnDefinition="INTEGER NOT NULL DEFAULT 1")
    private int version;

    private PessimisticProduct() {
    }

    public PessimisticProduct(int id, String name, int stock) {
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
    
    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
}
