package de.kstm.haushalt.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.kstm.haushalt.model.PayerMonthlyEarning;
import de.kstm.haushalt.service.IncomeService;

@Controller
@RequestMapping("/payer/{payerId}/income")
public class IncomeController {
	private IncomeService incomeService;

	public void setIncomeService(IncomeService incomeService) {
		this.incomeService = incomeService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<PayerMonthlyEarning> getAllEarnings(@PathVariable long payerId) {
		return incomeService.getAllIncomesForPayer(payerId);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PayerMonthlyEarning> createIncome(@RequestBody PayerMonthlyEarning income, @PathVariable long payerId) {
		PayerMonthlyEarning newIncome = incomeService.createOrUpdateIncome(income);
		return ControllerHelper.getNewlyCreatedRequestEntity(income,
				"{year}/{month}", payerId, 
				newIncome.getYear(), newIncome.getMonth());
	}
	
	@RequestMapping(value="{year}/{month}", method = RequestMethod.PUT)
	public PayerMonthlyEarning updateIncome(@RequestBody PayerMonthlyEarning income, @PathVariable long payerId,
			@PathVariable int year, @PathVariable int month) {
		return incomeService.createOrUpdateIncome(income);
	}
}
