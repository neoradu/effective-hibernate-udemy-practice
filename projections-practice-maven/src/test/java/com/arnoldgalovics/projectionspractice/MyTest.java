package com.arnoldgalovics.projectionspractice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arnoldgalovics.projectionspractice.repository.ProductRepository;
import com.arnoldgalovics.projectionspractice.service.MyProductService;


@SpringBootTest
public class MyTest {
    
    @Autowired
    MyProductService service;
    @Autowired
    ProductRepository repository;
    
    //@Test
    public void testProjections() {
        service.getProductNamesOptimised();
    }
    
    @Test
    public void testProjectionSpringDataRepository() {
        repository.findSingleById(1);
    }
    
    
}
