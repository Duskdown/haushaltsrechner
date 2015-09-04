package de.kstm.haushalt.service;

import java.util.List;

import de.kstm.haushalt.model.PayerMonthlyEarning;
import de.kstm.haushalt.repository.PayerMonthlyEarningRepository;

public class IncomeServiceImpl implements IncomeService {
	
	private PayerMonthlyEarningRepository incomeRepository;
	
	public void setPayerMonthlyearningRepository(PayerMonthlyEarningRepository incomeRepository) {
		this.incomeRepository = incomeRepository;
	}

	@Override
	public PayerMonthlyEarning createOrUpdateIncome(PayerMonthlyEarning income) {
		return incomeRepository.save(income);
	}

	@Override
	public List<PayerMonthlyEarning> getAllIncomesForPayer(long payerId) {
		return incomeRepository.findAllByPayerId(payerId);
	}

	@Override
	public List<PayerMonthlyEarning> getIncomeForYearAndPayer(long payerId,
			int year) {
		return incomeRepository.findAllByYearAndPayerId(year, payerId);
	}

	@Override
	public PayerMonthlyEarning getIncomeForMonthAndPayer(long payerId,
			int year, int month) {
		return incomeRepository.findOneByYearAndMonthAndPayerId(year, month, payerId);
	}
}
