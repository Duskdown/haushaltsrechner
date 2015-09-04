package de.kstm.haushalt.dto;

import de.kstm.haushalt.model.Payer;

public class Statistic {
	private Payer payer;

	public Statistic() {
	}
	
	public Payer getPayer() {
		return payer;
	}

	public void setPayer(Payer payer) {
		this.payer = payer;
	}
}
