package com.arnoldgalovics.concurrencycontrolpractice.domain.optimistic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

/**
 * A very simple representation of a Product that is used for demonstration purposes.
 */
@Entity
@Table(name = "opt_products_versionless")
@DynamicUpdate
@OptimisticLocking(type = OptimisticLockType.DIRTY)
public class VersionlessOptimisticProduct {
    @Id
    private int id;
    private String name;
    private int stock;

    private VersionlessOptimisticProduct() {
    }

    public VersionlessOptimisticProduct(int id, String name, int stock) {
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
