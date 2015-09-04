package de.kstm.haushalt.service;

import de.kstm.haushalt.model.Product;

public interface ProductService {
	Product createOrModifyProduct(Product product);
	Product getProductByName(String productName);
	Product getById(long productId);
}
