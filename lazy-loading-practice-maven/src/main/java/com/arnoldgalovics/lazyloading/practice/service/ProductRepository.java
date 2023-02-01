package com.arnoldgalovics.lazyloading.practice.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arnoldgalovics.lazyloading.practice.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("FROM Product products JOIN FETCH products.reviews")
    List<Product> retrieveAll();
}
