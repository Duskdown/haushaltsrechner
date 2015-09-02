package de.kstm.haushalt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="PRODUCTS")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String category;
	private double defaultPrice;
	
    public Product() {
    }
    
    public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setDefaultPrice(double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}
	
	public double getDefaultPrice() {
		return defaultPrice;
	}
}
