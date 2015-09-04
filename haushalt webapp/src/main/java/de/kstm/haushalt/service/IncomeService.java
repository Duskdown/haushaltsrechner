package de.kstm.haushalt.service;

import java.util.List;

import de.kstm.haushalt.model.Income;

public interface IncomeService {
	Income createOrModifyIncome(Income income);
	List<Income> getAllIncomesForPayer(long payerId);
	List<Income> getIncomeForYearAndPayer(long payerId, int year);
	Income getIncomeForMonthAndPayer(long payerId, int year, int month);
}
