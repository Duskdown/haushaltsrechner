package de.kstm.haushalt.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.kstm.haushalt.model.Bill;
import de.kstm.haushalt.repository.BillRepository;

@Service
public class BillServiceImpl implements BillService {

	private BillRepository billRepository;

	@Autowired
	public void setBillRepository(BillRepository billRepository) {
		this.billRepository = billRepository;
	}

	@Override
	public List<Bill> getAllBillsForPayer(long payerId) {
		return billRepository.findAllByPayerId(payerId);
	}

	@Override
	public List<Bill> getBillsFromYearForPayer(long payerId, int year) {
		Date startDate = getDateForYearAndMonth(year, 1).getTime();
		Date endDate = getLastDateForYearAndMonth(year, 12).getTime();
		
		return billRepository.findAllByPayerIdAndBillDateBetween(payerId, startDate, endDate);
	}

	@Override
	public List<Bill> getBillsFromYearAndMonthForPayer(long payerId, int year,
			int month) {
		Date startDate = getDateForYearAndMonth(year, month).getTime();
		Date endDate = getLastDateForYearAndMonth(year, month).getTime();
		
		return billRepository.findAllByPayerIdAndBillDateBetween(payerId, startDate, endDate);
	}

	@Override
	public Bill createOrModifyBill(Bill bill) {
		return billRepository.save(bill);
	}

	@Override
	public Bill getBill(long billId) {
		return billRepository.getOne(billId);
	}

	@Override
	public void deleteBill(long billId) {
		billRepository.delete(billId);
	}
	
	private Calendar getDateForYearAndMonth(int year, int month) {
		Calendar date = Calendar.getInstance();
		date.set(year, month - 1, 1, 0, 0, 0);
		date.set(Calendar.MILLISECOND, 0);
		return date;
	}

	private Calendar getLastDateForYearAndMonth(int year, int month) {
		Calendar date = getDateForYearAndMonth(year, month + 1);
		date.add(Calendar.DAY_OF_MONTH, -1);
		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);
		date.set(Calendar.MILLISECOND, 999);
		return date;
	}
}
