package de.kstm.haushalt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.kstm.haushalt.model.Payer;

public interface PayerRepository extends JpaRepository<Payer, Long> {
	
}
