package de.kstm.haushalt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.kstm.haushalt.model.Payer;
import de.kstm.haushalt.repository.PayerMonthlyEarningRepository;
import de.kstm.haushalt.repository.PayerRepository;

@Service
public class PayerServiceImpl implements PayerService {

	private PayerRepository userRepository;
	private PayerMonthlyEarningRepository userMonthRepository;
	
	@Autowired
	public void setUserRepository(PayerRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setUserMonthRepository(PayerMonthlyEarningRepository userMonthRepository) {
		this.userMonthRepository = userMonthRepository;
	}
	
	@Override
	public Payer createOrModifyPayer(Payer user) {
		return userRepository.save(user);
	}

	@Override
	public void deletePayer(long id) {
		userRepository.delete(id);
	}

	@Override
	public List<Payer> getAllPayers() {
		return userRepository.findAll();
	}

	@Override
	public Payer getPayer(long id) {
		return userRepository.findOne(id);
	}
}
