package de.kstm.haushalt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//TODO Refactor name of class

@Entity(name="BILL_PRODUCTS")
public class BillProduct {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@ManyToOne
	private Product product;
	private double concretePrice;
	
	public BillProduct() {
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public double getConcretePrice() {
		return concretePrice;
	}
	
	public void setConcretePrice(double concretePrice) {
		this.concretePrice = concretePrice;
	}
}
