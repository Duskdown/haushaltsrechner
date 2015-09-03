package de.kstm.haushalt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.kstm.haushalt.model.PayerMonthlyEarning;

public interface PayerMonthlyEarningRepository extends JpaRepository<PayerMonthlyEarning, Long> {
	List<PayerMonthlyEarning> findAllByYearAndUserId(int year, long userId);
	List<PayerMonthlyEarning> findAllByYearAndMonthAndUserId(int year, int month, long userId);
}
