package com.arnoldgalovics.entitytransitions.practice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arnoldgalovics.entitytransitions.practice.domain.Product;
import com.arnoldgalovics.entitytransitions.practice.service.MyProductRepository;

@SpringBootTest
public class RaduTest {

    @Autowired 
    MyProductRepository productRepository;
    
    //@Test
    public void testEntityManagerMerge() {
        productRepository.decrementProductStock(1, 2);  
    }
    
    @Test
    public void testEntityManagerMerge02() {
        productRepository.create(3, 13);
    }
    
}
