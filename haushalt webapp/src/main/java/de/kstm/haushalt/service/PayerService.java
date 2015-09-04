package de.kstm.haushalt.service;

import java.util.List;

import de.kstm.haushalt.model.Payer;

public interface PayerService {
	Payer createOrModifyPayer(Payer payer);
	void deletePayer(long id);
	List<Payer> getAllPayers();
	Payer getPayer(long id);
}
