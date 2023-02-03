package com.arnoldgalovics.concurrencycontrolpractice;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arnoldgalovics.concurrencycontrolpractice.domain.pessimistic.PessimisticProduct;
import com.arnoldgalovics.concurrencycontrolpractice.support.TransactionalRunner;

@SpringBootTest
public class MyPessimistickLock {
    
    @Autowired
    EntityManager entityManager;
    
    @Autowired
    TransactionalRunner transactionRunner;
    
    //@Test
    @Transactional
    void testPessimisticForceIncrement() {
        PessimisticProduct product = 
                entityManager.find(PessimisticProduct.class, 1, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
    }
    
    //@Test
    void testPessimisticRead() {
        transactionRunner.doInTransaction(em -> {
            PessimisticProduct product = 
                    em.find(PessimisticProduct.class, 1, LockModeType.PESSIMISTIC_READ);
            transactionRunner.doInTransaction(em2 -> {
                PessimisticProduct product02 = 
                        em2.find(PessimisticProduct.class, 1, LockModeType.PESSIMISTIC_READ);
                       //product02.setStock(100);
                }
            );
        });
    }
    
    @Test
    void testPessimisticWrite() {
        transactionRunner.doInTransaction(em -> {
            PessimisticProduct product = 
                    em.find(PessimisticProduct.class, 1, LockModeType.PESSIMISTIC_WRITE);
            transactionRunner.doInTransaction(em2 -> {
                PessimisticProduct product02 = 
                        em2.find(PessimisticProduct.class, 1, LockModeType.PESSIMISTIC_WRITE);
                       //product02.setStock(100);
                }
            );
        });
    }
    
}
