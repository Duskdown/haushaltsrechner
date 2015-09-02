package de.kstm.haushalt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="FIXED_EXPENSES")
public class FixedExpense {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	private String name;
	private double cost;
	
	public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
