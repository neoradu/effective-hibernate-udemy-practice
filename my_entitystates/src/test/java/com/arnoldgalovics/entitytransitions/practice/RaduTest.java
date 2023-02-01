package com.arnoldgalovics.entitytransitions.practice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arnoldgalovics.entitytransitions.practice.domain.Product;
import com.arnoldgalovics.entitytransitions.practice.service.ProductRepository;

@SpringBootTest
public class RaduTest {

    @Autowired 
    ProductRepository productRepository;
    
    @Test
    public void testEntityManagerMerge() {
        Product p = productRepository.find(1);
        p.setStock(p.getStock() - 1);
        productRepository.save(p);
        
    }
}
