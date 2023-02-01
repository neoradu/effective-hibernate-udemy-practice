package com.arnoldgalovics.entitytransitions.practice.service;

import com.arnoldgalovics.entitytransitions.practice.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The class that can be used to interact with the {@link Product} entity on a database level.
 */
@Repository
public class MyProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Loads a {@link Product} from the database.
     * @param id the id of the {@link Product} to be loaded
     * @return the {@link Product} instance associated with the given id or null in case it cannot be found
     */
    @Transactional
    public Product find(int id) {
        return entityManager.find(Product.class, id);
    }

    /**
     * Saves the given product to the database.
     * @param product the product to be saved
     */
    @Transactional
    public void save(Product product) {
        entityManager.merge(product);
    }

    /**
     * Persists the given product to the database.
     * @param product the product to be persisted
     */
    @Transactional
    public void persist(Product product) {
        entityManager.persist(product);
    }
    
    @Transactional // Because in transactional context Product will be in managed state
    public void decrementProductStock(int id, int value) {
        Product p  = entityManager.find(Product.class, id);
        p.setStock(p.getStock() - value); 
    }
    
    @Transactional
    public void create(int id, int stock) {
        Product product =  new Product(id,"default", stock);
        entityManager.persist(product);
    }
}
