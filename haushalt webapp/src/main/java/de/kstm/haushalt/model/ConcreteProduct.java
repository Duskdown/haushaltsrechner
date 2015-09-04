package de.kstm.haushalt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="CONCERETE_PRODUCTS")
public class ConcreteProduct {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Bill bill;
	
	private double concretePrice;
	
	public ConcreteProduct() {
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
