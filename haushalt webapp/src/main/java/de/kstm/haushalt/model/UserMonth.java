package de.kstm.haushalt.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name="USER_MONTHS")
public class UserMonth {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@ManyToOne
	private User user;

	@OneToMany
	private List<FixedExpense> actualFixedExpenses;
	private double actualIncome;
	private int year;
	private int month;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<FixedExpense> getActualFixedExpenses() {
		return actualFixedExpenses;
	}

	public void setActualFixedExpenses(List<FixedExpense> actualFixedExpenses) {
		this.actualFixedExpenses = actualFixedExpenses;
	}

	public double getActualIncome() {
		return actualIncome;
	}

	public void setActualIncome(double actualIncome) {
		this.actualIncome = actualIncome;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}	
}
