package com.arnoldgalovics.flushing.practice;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import org.h2.engine.Session;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arnoldgalovics.flushing.practice.domain.Product;
import com.arnoldgalovics.flushing.practice.domain.ProductReview;
import com.arnoldgalovics.flushing.practice.support.TransactionalRunner;

@SpringBootTest
public class FlushingTest {
    
    private Logger logger = LoggerFactory.getLogger(FlushingTest.class);
    //@Autowired
    //EntityManager entityManager;
    
    @Autowired
    TransactionalRunner transactionalRunner;
    
    //@Test
    void testSimpleFlushing() {
        transactionalRunner.doInTransaction(em -> {
            Product p1 = new Product(1,"product01",10);
            Product p2 = new Product(2,"product02",10);
            em.persist(p1);
            logger.debug("Before flush()");
            em.flush();// <-- force a flush to the DB
            logger.debug("After flush()");
            em.persist(p2);
            logger.debug("Before transaction commit");
        });
    }
    
    //@Test
    void testFlushinJPQL() {
        transactionalRunner.doInTransaction(em -> {
            Product p1 = new Product(1,"product01",10);
            Product p2 = new Product(2,"product02",10);
            em.persist(p1);
            logger.debug("Before JPQL query");
            em.createQuery("FROM Product", Product.class).getResultList();
            logger.debug("After JPQL query");
            em.persist(p2);
            logger.debug("Before transaction commit");
        });   
    }
    
    @Test
    void testFlushinJPQLForDifferentEntity() {
        transactionalRunner.doInTransaction(entityManager -> {
            //entityManager.setFlushMode(FlushModeType.COMMIT);
           // entityManager.unwrap(Session.class).set .setFlushMode(FlushModeType.COMMIT);
            Product p1 = new Product(1,"product01",10);
            Product p2 = new Product(2,"product02",10);
            entityManager.persist(p1);
            logger.debug("Before JPQL query");
            entityManager.createQuery("FROM ProductReview", ProductReview.class).getResultList();
            logger.debug("After JPQL query");
            entityManager.persist(p2);
            logger.debug("Before transaction commit");
        });   
    }
    
    //@Test
    void testFlushinJPQLForDifferentEntityNativeQuerry() {
        transactionalRunner.doInTransaction(entityManager -> {
            entityManager.setFlushMode(FlushModeType.COMMIT);
            Product p1 = new Product(1,"product01",10);
            Product p2 = new Product(2,"product02",10);
            entityManager.persist(p1);
            logger.debug("Before Native query");
            entityManager.createNativeQuery("SELECT * FROM product_reviews", ProductReview.class).getResultList();
            logger.debug("After JPQL query");
            entityManager.persist(p2);
            logger.debug("Before transaction commit");
        });   
    }
}
