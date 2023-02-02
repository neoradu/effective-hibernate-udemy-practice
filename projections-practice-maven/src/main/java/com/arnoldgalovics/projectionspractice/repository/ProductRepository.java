package com.arnoldgalovics.projectionspractice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arnoldgalovics.projectionspractice.domain.Product;
import com.arnoldgalovics.projectionspractice.domain.ProductInterfaceForProjection;
import com.arnoldgalovics.projectionspractice.domain.ProductProjection;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    ProductProjection findSingleById(int id);    
    List<ProductInterfaceForProjection> findAllProjectedBy(); 
}
