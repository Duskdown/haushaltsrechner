package de.kstm.haushalt.repository;

import java.util.Calendar;
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
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import de.kstm.haushalt.Application;
import de.kstm.haushalt.model.Bill;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DatabaseSetup(BillRepositoryIT.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { BillRepositoryIT.DATASET })
@DirtiesContext
public class BillRepositoryIT {
	protected static final String DATASET = "classpath:datasets/BillRepositoryIT-dataset.xml";

	@Autowired
	BillRepository billRepository;

	@Test
	public void findAllBillsForSpecificMonth() {
		// arrrange
		Calendar startDate = Calendar.getInstance();
		startDate.set(2015, 2, 1); // the month is 0 based so basically march is
									// 2 and april is 3

		Calendar endDate = Calendar.getInstance();
		endDate.set(2015, 3, 1);
		endDate.add(Calendar.DAY_OF_MONTH, -1);

		// act
		List<Bill> result = billRepository.findAllByPayerIdAndBillDateBetween(
				1l, startDate.getTime(), endDate.getTime());

		// assert
		Assert.assertEquals(3, result.size());
		Assert.assertEquals(1, result.get(0).getId());
		Assert.assertEquals(2, result.get(1).getId());
		Assert.assertEquals(4, result.get(2).getId());
	}

	@Test
	public void findAllBillsForSpecificYear() {
		// arrrange
		Calendar startDate = Calendar.getInstance();
		startDate.set(2016, 0, 1); // the month is 0 based so basically march is
									// 2 and april is 3

		Calendar endDate = Calendar.getInstance();
		endDate.set(2017, 0, 1);
		endDate.add(Calendar.DAY_OF_MONTH, -1);

		// act
		List<Bill> result = billRepository.findAllByPayerIdAndBillDateBetween(
				2l, startDate.getTime(), endDate.getTime());

		// assert
		Assert.assertEquals(6, result.size());
		Assert.assertEquals(7, result.get(0).getId());
		Assert.assertEquals(8, result.get(1).getId());
		Assert.assertEquals(9, result.get(2).getId());
		Assert.assertEquals(10, result.get(3).getId());
		Assert.assertEquals(11, result.get(4).getId());
		Assert.assertEquals(12, result.get(5).getId());
	}
	
	@Test
	public void findAllBillsForPayer() {
		//arrange
		long payerId = 2;
		
		//act
		List<Bill> result = billRepository.findAllByPayerId(payerId);
		
		//assert
		Assert.assertEquals(8, result.size());
		Assert.assertEquals(6, result.get(0).getId());
		Assert.assertEquals(7, result.get(1).getId());
		Assert.assertEquals(8, result.get(2).getId());
		Assert.assertEquals(9, result.get(3).getId());
		Assert.assertEquals(10, result.get(4).getId());
		Assert.assertEquals(11, result.get(5).getId());
		Assert.assertEquals(12, result.get(6).getId());
		Assert.assertEquals(13, result.get(7).getId());
		
	}
}
