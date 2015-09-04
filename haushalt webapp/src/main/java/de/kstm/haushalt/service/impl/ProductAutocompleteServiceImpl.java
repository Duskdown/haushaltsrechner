package de.kstm.haushalt.service.impl;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import de.kstm.haushalt.model.Product;
import de.kstm.haushalt.repository.ProductRepository;
import de.kstm.haushalt.service.ProductAutocompleteService;

public class ProductAutocompleteServiceImpl implements ProductAutocompleteService {
	
	private ProductRepository productRepository;
	
	public ProductRepository getProductRepository() {
		return productRepository;
	}

	@Override
	public List<Product> getRecommendedProducts(String input) {
		throw new NotImplementedException("getRecommendedProducts");
	}
}
