package de.kstm.haushalt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="PAYERS")
public class Payer {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<FixedExpense> defaultExpenses;
	
    private String name;
    private double defaultIncome;
    //private Month month;
    
  	public Payer() {
    }

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
    
    public double getDefaultIncome() {
		return defaultIncome;
	}

	public void setDefaultIncome(double income) {
		this.defaultIncome = income;
	}
	
	public List<FixedExpense> getDefaultExpenses() {
		return defaultExpenses;
	}
	
	public void setDefaultExpenses(List<FixedExpense> defaultExpenses) {
		this.defaultExpenses = defaultExpenses;
	}
}
