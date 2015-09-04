package de.kstm.haushalt.service;

import java.util.List;

import de.kstm.haushalt.model.Product;

public interface ProductAutocompleteService {
	List<Product> getRecommendedProducts(String input);
}
