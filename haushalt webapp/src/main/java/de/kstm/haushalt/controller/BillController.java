package de.kstm.haushalt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.kstm.haushalt.model.Bill;
import de.kstm.haushalt.service.BillService;

@Controller
@RequestMapping("/payer/{payerId}/bill")
public class BillController {
	private BillService billService;

	@Autowired
	public void setBillService(BillService billService) {
		this.billService = billService;
	}

	public BillService getBillService() {
		return billService;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Bill> createBill(@RequestBody Bill bill, @PathVariable long payerId) {
		Bill newBill = billService.createOrModifyBill(bill);
		return ControllerHelper.getNewlyCreatedRequestEntity(newBill, "/{billId}", payerId, newBill.getId());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Bill> getAllBills(@PathVariable long payerId) {
		return billService.getAllBillsForPayer(payerId);
	}
	
	@RequestMapping(value="/{billId}", method = RequestMethod.PUT)
	public Bill updateBill(@RequestBody Bill bill, @PathVariable long billId) {
		return billService.createOrModifyBill(bill);
	}
	
	@RequestMapping(value="/{billId}", method = RequestMethod.DELETE)
	public void deleteBill(@PathVariable long billId) {
		billService.deleteBill(billId);
	}
	
	@RequestMapping(value="/{billId}", method = RequestMethod.GET)
	public Bill getBill(@PathVariable long billId) {
		return billService.getBill(billId);
	}
	
	@RequestMapping(value="/date/{year}", method = RequestMethod.GET)
	public List<Bill> getBillsFromYear(@PathVariable long payerId, @PathVariable int year) {
		return billService.getBillsFromYearForPayer(payerId, year);
	}
	
	@RequestMapping(value="/date/{year}/{month}", method = RequestMethod.GET)
	public List<Bill> getBillsFromYearAndMonth(@PathVariable long payerId, @PathVariable int year, @PathVariable int month) {
		return billService.getBillsFromYearAndMonthForPayer(payerId, year, month);
	}
}
