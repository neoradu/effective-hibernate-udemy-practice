package com.arnoldgalovics.concurrencycontrolpractice;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.arnoldgalovics.concurrencycontrolpractice.domain.optimistic.OptimisticProduct;
import com.arnoldgalovics.concurrencycontrolpractice.domain.optimistic.VersionlessOptimisticProduct;
import com.arnoldgalovics.concurrencycontrolpractice.support.TransactionalRunner;

@SpringBootTest
public class MyOptimisticLockingTest {
	private Logger logger = LoggerFactory.getLogger(MyOptimisticLockingTest.class);
	
	@Autowired
	private TransactionalRunner txRunner;

    @PersistenceContext
    private EntityManager emTest;
    
	//@Test
	void testOptimisticLocking01() {
		try {
			txRunner.doInTransaction(em -> {//First transaction
				OptimisticProduct product01 = em.find(OptimisticProduct.class, 1);
				
				txRunner.doInTransaction(em02 -> {//Inner transaction
					OptimisticProduct product02 = em.find(OptimisticProduct.class, 1);
					product02.setStock(product02.getStock() + 2);// set stock=12
				});
				// This causes ObjectOptimisticLockingFailureException to be thrown from the first TransactionalRunner
				product01.setStock(product01.getStock() - 2); // set stock=8
						
			});
		} catch (ObjectOptimisticLockingFailureException ex) {
			logger.error("!!! ObjectOptimisticLockingFailureException");
			// redo the operation
			txRunner.doInTransaction(em -> {
				OptimisticProduct product01 = em.find(OptimisticProduct.class, 1);
				product01.setStock(product01.getStock() - 2); // set stock=10
			});
		}
		
		OptimisticProduct productFinal = emTest.find(OptimisticProduct.class, 1);
		logger.error(String.format("productFinal stock:%d", productFinal.getStock()));
	}
	
	//@Test
	void testOptimisticLocking02() {

			txRunner.doInTransaction(em -> {//First transaction
				VersionlessOptimisticProduct product01 = em.find(VersionlessOptimisticProduct.class, 1);
				
				txRunner.doInTransaction(em02 -> {//Inner transaction
					VersionlessOptimisticProduct product02 = em.find(VersionlessOptimisticProduct.class, 1);
					product02.setName("MODIFIED NAME");// set name="MODIFIED NAME"
				});
				// This causes ObjectOptimisticLockingFailureException to be thrown from the first TransactionalRunner
				product01.setStock(product01.getStock() - 2); // set stock=8
				//product01.setName("PLM GLOBAL"); // set stock=8
						
			});

		
		VersionlessOptimisticProduct productFinal = emTest.find(VersionlessOptimisticProduct.class, 1);
		logger.error(String.format("productFinal stock:%d", productFinal.getStock()));
	}
	
	@Test
	void testOptimisticLocking03() {
		try {
			txRunner.doInTransaction(em -> {//First transaction
				//LockModeType.OPTIMISTIC --> forces Hibernate to do a version check at the end of the transaction
				// If this is changed ObjectOptimisticLockingFailureException will be thrown
				OptimisticProduct product01 = em.find(OptimisticProduct.class, 1, LockModeType.OPTIMISTIC); 
				//Image calculating something important here
				txRunner.doInTransaction(em02 -> {//Inner transaction
					OptimisticProduct product02 = em.find(OptimisticProduct.class, 1);
					product02.setStock(product02.getStock() + 2);// set stock=12
				});
						
			});
		} catch (ObjectOptimisticLockingFailureException ex) {
			logger.error("!!! ObjectOptimisticLockingFailureException"); // this will be thrown
		}
		
		OptimisticProduct productFinal = emTest.find(OptimisticProduct.class, 1);
		logger.error(String.format("productFinal stock:%d", productFinal.getStock()));
	}
}
