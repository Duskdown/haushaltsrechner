package de.kstm.haushalt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.kstm.haushalt.model.Payer;
import de.kstm.haushalt.service.PayerService;

@Controller
@RequestMapping("/payer")
public class PayerController {
	private PayerService payerService;

	@Autowired
	public void setPayerService(PayerService payerService) {
		this.payerService = payerService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Payer> getPayers() {
		return payerService.getAllPayers();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Payer> add(@RequestBody Payer payer) {
		Payer newPayer = payerService.createOrModifyPayer(payer);
		return ControllerHelper.getNewlyCreatedRequestEntity(newPayer, "{id}", newPayer.getId());
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long id) {
		payerService.deletePayer(id);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable long id, @RequestBody Payer payer) {
		payerService.createOrModifyPayer(payer);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public void getPayer(@PathVariable long id) {
		payerService.getPayer(id);
	}
}
