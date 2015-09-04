package de.kstm.haushalt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.kstm.haushalt.model.Payer;
import de.kstm.haushalt.repository.PayerRepository;
import de.kstm.haushalt.service.PayerService;

@Service
public class PayerServiceImpl implements PayerService {

	private PayerRepository payerRepository;
	
	@Autowired
	public void setPayerRepository(PayerRepository payerRepository) {
		this.payerRepository = payerRepository;
	}
	
	/*@Autowired
	public void setPayerMonthRepository(PayerMonthlyEarningRepository payerMonthRepository) {
		this.payerMonthRepository = payerMonthRepository;
	}*/
	
	@Override
	public Payer createOrModifyPayer(Payer payer) {
		return payerRepository.save(payer);
	}

	@Override
	public void deletePayer(long id) {
		payerRepository.delete(id);
	}

	@Override
	public List<Payer> getAllPayers() {
		return payerRepository.findAll();
	}

	@Override
	public Payer getPayer(long id) {
		return payerRepository.findOne(id);
	}
}
