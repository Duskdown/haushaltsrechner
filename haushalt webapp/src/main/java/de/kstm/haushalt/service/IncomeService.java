package de.kstm.haushalt.service;

import java.util.List;

import de.kstm.haushalt.model.PayerMonthlyEarning;

public interface IncomeService {
	PayerMonthlyEarning createOrUpdateIncome(PayerMonthlyEarning income);
	List<PayerMonthlyEarning> getAllIncomesForPayer(long payerId);
	List<PayerMonthlyEarning> getIncomeForYearAndPayer(long payerId, int year);
	PayerMonthlyEarning getIncomeForMonthAndPayer(long payerId, int year, int month);
}
