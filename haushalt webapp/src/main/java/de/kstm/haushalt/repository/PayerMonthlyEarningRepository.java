package de.kstm.haushalt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.kstm.haushalt.model.PayerMonthlyEarning;

public interface PayerMonthlyEarningRepository extends JpaRepository<PayerMonthlyEarning, Long> {
	List<PayerMonthlyEarning> findAllByYearAndPayerId(int year, long payerId);
	List<PayerMonthlyEarning> findAllByYearAndMonthAndPayerId(int year, int month, long payerId);
}
