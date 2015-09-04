package de.kstm.haushalt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.kstm.haushalt.model.Product;
import de.kstm.haushalt.repository.ProductRepository;
import de.kstm.haushalt.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product createOrModifyProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getProductByName(String productName) {
		return productRepository.findOneByName(productName);
	}

	@Override
	public Product getById(long productId) {
		return productRepository.findOne(productId);
	}

}
