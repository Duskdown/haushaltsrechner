package de.kstm.haushalt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.kstm.haushalt.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {
	List<Income> findAllByPayerId(long payerId);
	List<Income> findAllByYearAndPayerId(int year, long payerId);
	Income findOneByYearAndMonthAndPayerId(int year, int month, long payerId);
}
