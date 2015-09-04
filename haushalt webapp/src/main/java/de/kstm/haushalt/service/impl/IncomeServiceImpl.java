package de.kstm.haushalt.service.impl;

import java.util.List;

import de.kstm.haushalt.model.Income;
import de.kstm.haushalt.repository.IncomeRepository;
import de.kstm.haushalt.service.IncomeService;

public class IncomeServiceImpl implements IncomeService {
	
	private IncomeRepository incomeRepository;
	
	public void setPayerMonthlyearningRepository(IncomeRepository incomeRepository) {
		this.incomeRepository = incomeRepository;
	}

	@Override
	public Income createOrUpdateIncome(Income income) {
		return incomeRepository.save(income);
	}

	@Override
	public List<Income> getAllIncomesForPayer(long payerId) {
		return incomeRepository.findAllByPayerId(payerId);
	}

	@Override
	public List<Income> getIncomeForYearAndPayer(long payerId,
			int year) {
		return incomeRepository.findAllByYearAndPayerId(year, payerId);
	}

	@Override
	public Income getIncomeForMonthAndPayer(long payerId,
			int year, int month) {
		return incomeRepository.findOneByYearAndMonthAndPayerId(year, month, payerId);
	}
}
