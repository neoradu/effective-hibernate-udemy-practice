package com.arnoldgalovics.lazyloading.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arnoldgalovics.lazyloading.practice.domain.Product;
import com.arnoldgalovics.lazyloading.practice.domain.ProductReview;
import com.arnoldgalovics.lazyloading.practice.service.MyProductService;
import com.arnoldgalovics.lazyloading.practice.service.ProductRepository;


@SpringBootTest
public class MyTest {
    private Logger logger = LoggerFactory.getLogger(MyTest.class);
    @Autowired
    MyProductService service;
    
    @Autowired
    ProductRepository productRepository;
    
    //@Test
    void testFetching01() {
        Collection<ProductReview> productReviews = service.getReviewsForProduct(1);
        logger.debug(String.format("productReviews is not null %b", productReviews != null));
        logger.debug(String.format("productReviews[0].getRating() %d",
                                    productReviews.iterator().next().getRating()));
        assertNotNull(productReviews);
    }
    
    //@Test
    void testCountLazy01() {
        int count = service.countReviewsForProduct(1);
        logger.debug(String.format("product 1 has %d reviews", count));

    }
    
    //@Test
    void testFetching02() {
        Collection<ProductReview> productReviews = service.getReviewsByProductHibernateAPI(1);
        logger.debug(String.format("productReviews[0].getRating() %d",
                                    productReviews.iterator().next().getRating()));
    }
    
    @Test
    void TestNplus1() {
        //service.countAvailableProducts();
        List<Product> products =  productRepository.retrieveAll();
    }
    
}
