package de.kstm.haushalt.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.kstm.haushalt.model.Bill;
import de.kstm.haushalt.model.ConcreteProduct;
import de.kstm.haushalt.model.Product;
import de.kstm.haushalt.repository.BillRepository;
import de.kstm.haushalt.service.BillService;
import de.kstm.haushalt.service.ProductService;

@Service
@Transactional
public class BillServiceImpl implements BillService {

	private ProductService productService;
	private BillRepository billRepository;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Autowired
	public void setBillRepository(BillRepository billRepository) {
		this.billRepository = billRepository;
	}

	@Override
	public List<Bill> getAllBillsForPayer(long payerId) {
		return billRepository.findAllByPayerId(payerId);
	}

	@Override
	public List<Bill> getBillsFromYearForPayer(long payerId, int year) {
		Date startDate = getDateForYearAndMonth(year, 1).getTime();
		Date endDate = getLastDateForYearAndMonth(year, 12).getTime();
		
		return billRepository.findAllByPayerIdAndBillDateBetween(payerId, startDate, endDate);
	}

	@Override
	public List<Bill> getBillsFromYearAndMonthForPayer(long payerId, int year,
			int month) {
		Date startDate = getDateForYearAndMonth(year, month).getTime();
		Date endDate = getLastDateForYearAndMonth(year, month).getTime();
		
		return billRepository.findAllByPayerIdAndBillDateBetween(payerId, startDate, endDate);
	}

	@Override
	public Bill createOrModifyBill(Bill bill) {
		for(ConcreteProduct concreteProduct : bill.getProducts()) {
			if(concreteProduct.getProduct() == null) {
				//TODO throw exception
			} else {
				Product baseProduct = concreteProduct.getProduct();
				if(baseProduct.getId() < 1) { // if no id is available 
					Product productWithSameName = productService
							.getProductByName(baseProduct.getName());
					if(productWithSameName == null) {
						Product newBaseProduct = productService.createOrModifyProduct(baseProduct);
						concreteProduct.setProduct(newBaseProduct);
					} else {
						concreteProduct.setProduct(productWithSameName);
					}
				} else {
					Product persistedProduct = productService.getById(baseProduct.getId());
					if(persistedProduct == null || persistedProduct.getName() != baseProduct.getName()) {
						baseProduct.setId(0);
						productService.createOrModifyProduct(baseProduct);
					}
				}
			}
		}
		return billRepository.save(bill);
	}

	@Override
	public Bill getBill(long billId) {
		return billRepository.getOne(billId);
	}

	@Override
	public void deleteBill(long billId) {
		billRepository.delete(billId);
	}
	
	private Calendar getDateForYearAndMonth(int year, int month) {
		Calendar date = Calendar.getInstance();
		date.set(year, month - 1, 1, 0, 0, 0);
		date.set(Calendar.MILLISECOND, 0);
		return date;
	}

	private Calendar getLastDateForYearAndMonth(int year, int month) {
		Calendar date = getDateForYearAndMonth(year, month + 1);
		date.add(Calendar.DAY_OF_MONTH, -1);
		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);
		date.set(Calendar.MILLISECOND, 999);
		return date;
	}
}
