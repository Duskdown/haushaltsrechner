package de.kstm.haushalt.service;

import java.util.List;

import de.kstm.haushalt.model.Bill;

public interface BillService {
	List<Bill> getAllBillsForPayer(long payerId);
	List<Bill> getBillsFromYearForPayer(long payerId, int year);
	List<Bill> getBillsFromYearAndMonthForPayer(long payerId, int year, int month);
	Bill createOrModifyBill(Bill bill);
	Bill getBill(long billId);
	void deleteBill(long billId);
}
