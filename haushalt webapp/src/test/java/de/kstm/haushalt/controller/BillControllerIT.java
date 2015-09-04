package de.kstm.haushalt.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;

import de.kstm.haushalt.Application;
import de.kstm.haushalt.model.Bill;
import de.kstm.haushalt.model.ConcreteProduct;
import de.kstm.haushalt.model.Payer;
import de.kstm.haushalt.model.Product;
import de.kstm.haushalt.repository.BillRepository;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@DatabaseSetup(BillControllerIT.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { BillControllerIT.DATASET })
@DirtiesContext
@IntegrationTest("server.port:0")
public class BillControllerIT {
	protected static final String DATASET = "classpath:datasets/BillControllerIT-dataset.xml";
	
	@Value("${local.server.port}")
	private int port;
	
	@Autowired
	private BillRepository billRepository;
	private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	
	@Before
	public void setUp() {
		RestAssured.port = port;
		requestSpecBuilder.setContentType(ContentType.JSON).addHeader("Accept",
				ContentType.JSON.getAcceptHeader());
	}
	
	@org.junit.After
	public void tearDown() {
		billRepository.deleteAll();
	}

	@Test
	public void addBillTest() {
		// arrange
		Bill bill = getNewBill();
		requestSpecBuilder.setBody(bill);
		
		// act | assert
		RestAssured.given(requestSpecBuilder.build()).when().post("/payer/1/bill")
				.then().statusCode(HttpStatus.CREATED.value())
				.header("location", Matchers.containsString("/payer/1/bill/1"));
		
		Assert.assertEquals(1, billRepository.count());
	}
	
	@Test
	public void updateBillTest() {
		double expectedPrice = 100;
		Bill bill = getNewBill();
		Bill persistedBill = billRepository.save(bill);
		persistedBill.setTotalPrice(expectedPrice);
		requestSpecBuilder.setBody(persistedBill);
		
		// act
		RestAssured.given(requestSpecBuilder.build()).when().put("/payer/1/bill/1");
		
		//assert
		Assert.assertEquals(1, billRepository.count());
		Assert.assertEquals(expectedPrice, billRepository.findAll().get(0).getTotalPrice(), 0);
	}
	
	private Bill getNewBill() {
		Payer payer = new Payer();
		payer.setId(1);
		
		Product product = new Product();
		product.setCategory("category");
		product.setDefaultPrice(49.99);
		product.setName("Name");

		List<ConcreteProduct> products = new ArrayList<>();
		ConcreteProduct concreteProduct = new ConcreteProduct();
		concreteProduct.setConcretePrice(15);
		concreteProduct.setProduct(product);

		Bill bill = new Bill();
		bill.setBillDate(Calendar.getInstance().getTime());
		bill.setTotalPrice(50);
		bill.setProducts(products);
		bill.setPayer(payer);
		
		return bill;
	}
}
