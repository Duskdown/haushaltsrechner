package de.kstm.haushalt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.kstm.haushalt.model.Product;
import de.kstm.haushalt.service.ProductAutocompleteService;

@Controller
@RequestMapping("/product")
public class ProductController {

	private ProductAutocompleteService productService;

	public void setProductService(ProductAutocompleteService productService) {
		this.productService = productService;
	}
	
	@RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
	public List<Product> getAutocomplete(@RequestParam String input) {
		return productService.getRecommendedProducts(input);
	}
}
