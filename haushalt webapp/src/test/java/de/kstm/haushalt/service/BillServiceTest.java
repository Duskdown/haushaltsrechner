package de.kstm.haushalt.service;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.kstm.haushalt.model.Bill;
import de.kstm.haushalt.repository.BillRepository;
import de.kstm.haushalt.service.impl.BillServiceImpl;

public class BillServiceTest {

	private BillServiceImpl billService;
	private BillRepository billRepositoryMock;

	@Before
	public void setUp() {
		billRepositoryMock = mock(BillRepository.class);

		billService = new BillServiceImpl();
		billService.setBillRepository(billRepositoryMock);
	}

	@Test
	public void getAllBillsByYearParsesInputCorrectTest() {
		// arrange
		Bill firstBill = new Bill();
		Bill secondBill = new Bill();
		firstBill.setId(1);
		secondBill.setId(2);

		List<Bill> bills = new ArrayList<>();
		bills.add(firstBill);
		bills.add(secondBill);

		int year = 2015;
		Calendar expectedStartDate = Calendar.getInstance();
		expectedStartDate.set(year, 0, 1, 0, 0, 0);
		expectedStartDate.set(Calendar.MILLISECOND, 0);
		
		Calendar expectedEndDate = Calendar.getInstance();
		expectedEndDate.set(year, 11, 31, 23, 59, 59);
		expectedEndDate.set(Calendar.MILLISECOND, 999);
		
		when(billRepositoryMock.findAllByPayerIdAndBillDateBetween(
						anyLong(), any(Date.class), any(Date.class))).thenReturn(bills);

		// act
		List<Bill> result = billService.getBillsFromYearForPayer(1l, year);

		// assert
		verify(billRepositoryMock, times(1)).findAllByPayerIdAndBillDateBetween(eq(1l),
						eq(expectedStartDate.getTime()),
						eq(expectedEndDate.getTime()));
		
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(1, result.get(0).getId());
		Assert.assertEquals(2, result.get(1).getId());
	}
	
	@Test
	public void getAllBillByMonthParsesInputCorrectTest() {
		// arrange
		Bill firstBill = new Bill();
		Bill secondBill = new Bill();
		firstBill.setId(1);
		secondBill.setId(2);

		List<Bill> bills = new ArrayList<>();
		bills.add(firstBill);
		bills.add(secondBill);

		int year = 2015;
		int month = 2;
		Calendar expectedStartDate = Calendar.getInstance();
		expectedStartDate.set(year, month - 1, 1, 0, 0, 0);
		expectedStartDate.set(Calendar.MILLISECOND, 0);
		
		Calendar expectedEndDate = Calendar.getInstance();
		expectedEndDate.set(year, month - 1, 28, 23, 59, 59);
		expectedEndDate.set(Calendar.MILLISECOND, 999);
		
		when(billRepositoryMock.findAllByPayerIdAndBillDateBetween(
						anyLong(), any(Date.class), any(Date.class))).thenReturn(bills);

		// act
		List<Bill> result = billService.getBillsFromYearAndMonthForPayer(1l, year, month);

		// assert
		verify(billRepositoryMock, times(1)).findAllByPayerIdAndBillDateBetween(eq(1l),
						eq(expectedStartDate.getTime()),
						eq(expectedEndDate.getTime()));
		
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(1, result.get(0).getId());
		Assert.assertEquals(2, result.get(1).getId());
	}
}
