package com.sahoora.cxfdemo.orders;

import static org.junit.Assert.assertTrue;

import javax.xml.ws.soap.SOAPFaultException;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sahoora.schema.order.ObjectFactory;
import com.sahoora.schema.order.OrderInquiryResponseType;
import com.sahoora.schema.order.OrderInquiryType;
import com.sahoora.service.orders.Orders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-beans.xml"})
public class DefaultOrdersEndpointTest {

	@Autowired
	private JaxWsProxyFactoryBean testOrdersClient;
	
	private Orders orderService;
	private OrderInquiryType orderInquiryType; 
	
	@Before
	public void setUp() throws Exception {
		orderService = testOrdersClient.create(Orders.class);
		ObjectFactory factory = new ObjectFactory();
		orderInquiryType = factory.createOrderInquiryType();
		orderInquiryType.setAccountId(99);
		orderInquiryType.setEan13(123456789L);
		orderInquiryType.setOrderQuantity(4);
		orderInquiryType.setUniqueOrderId(42333);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProcessOrderPlacement_Success() {
		OrderInquiryResponseType response = orderService.processOrderPlacement(orderInquiryType);
		assertTrue(response.getAccount().getAccountId() == 99);
	}

	@Test(expected=SOAPFaultException.class)
	public void testProcessOrderPlacement_InvalidParam() 
		throws Exception {
		orderService.processOrderPlacement(null);
	}
}
