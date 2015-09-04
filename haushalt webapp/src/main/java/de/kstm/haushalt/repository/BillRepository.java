package de.kstm.haushalt.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.kstm.haushalt.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
	List<Bill> findAllByPayerIdAndBillDateBetween(long payerId, Date startDate, Date endDate);
	List<Bill> findAllByPayerId(long payerId);
}
