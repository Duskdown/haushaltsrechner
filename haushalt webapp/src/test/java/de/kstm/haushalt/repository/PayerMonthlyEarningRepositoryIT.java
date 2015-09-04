package de.kstm.haushalt.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DatabaseOperation;

import de.kstm.haushalt.Application;
import de.kstm.haushalt.model.PayerMonthlyEarning;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DatabaseSetup(PayerMonthlyEarningRepositoryIT.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { PayerMonthlyEarningRepositoryIT.DATASET })
@DirtiesContext
public class PayerMonthlyEarningRepositoryIT {
	protected static final String DATASET = "classpath:datasets/PayerMonthlyEarningRepositoryIT-dataset.xml";

	@Autowired
	PayerMonthlyEarningRepository payerMonthlyEarningRepository;

	@Test
	public void findAllPayerEarningsForSpecificMonth() {
		// act
		PayerMonthlyEarning result = payerMonthlyEarningRepository.findOneByYearAndMonthAndPayerId(2015, 2, 1);
		
		// assert
		Assert.assertEquals(2, result.getId());
	}
	

	@Test
	public void findAllPayerEarningsForSpecificYear() {
		// act
		List<PayerMonthlyEarning> result = payerMonthlyEarningRepository.findAllByYearAndPayerId(2016, 2);
		
		// assert
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(8, result.get(0).getId());
		Assert.assertEquals(9, result.get(1).getId());
	}
	
	@Test
	public void findAllPayerEarnings() {
		// act
		List<PayerMonthlyEarning> result = payerMonthlyEarningRepository.findAllByPayerId(1l);
				
		// assert
		Assert.assertEquals(5, result.size());
		Assert.assertEquals(1, result.get(0).getId());
		Assert.assertEquals(2, result.get(1).getId());
		Assert.assertEquals(3, result.get(2).getId());
		Assert.assertEquals(4, result.get(3).getId());
		Assert.assertEquals(5, result.get(4).getId());
	}
}
