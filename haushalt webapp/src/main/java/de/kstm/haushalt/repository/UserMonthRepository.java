package de.kstm.haushalt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.kstm.haushalt.model.UserMonth;

public interface UserMonthRepository extends JpaRepository<UserMonth, Long> {
	List<UserMonth> findAllByYear(int year);
	List<UserMonth> findAllByYearAndMonth(int year, int month);
}
