package de.kstm.haushalt.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="BILLS")
public class Bill {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	@ManyToOne
	private User payingUser;

	@OneToMany
	private List<BillProduct> products;
	
	//TODO: Check type of date
	private Date billDate;
	private double totalPrice;
	
	public Date getBillDate() {
		return billDate;
	}
	
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public List<BillProduct> getBillProducts() {
		return this.products;
	}
	
	public void setBillProducts(List<BillProduct> products) {
		this.products = products;
	}
}
