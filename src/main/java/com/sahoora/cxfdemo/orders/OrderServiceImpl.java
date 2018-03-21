package com.sahoora.cxfdemo.orders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;

import com.sahoora.schema.order.AccountType;
import com.sahoora.schema.order.BookType;
import com.sahoora.schema.order.ObjectFactory;
import com.sahoora.schema.order.OrderInquiryResponseType;
import com.sahoora.schema.order.OrderItemType;
import com.sahoora.schema.order.OrderStatusType;
import com.sahoora.schema.order.OrderType;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public OrderInquiryResponseType processOrder(int uniqueOerderId, int orderQuantity, int accountId, long ean13) {
		ObjectFactory factory = new ObjectFactory();
		OrderInquiryResponseType response = factory.createOrderInquiryResponseType();
		
		AccountType account = factory.createAccountType();
		account.setAccountId(accountId);
		
		response.setAccount(account);
		
		OrderItemType orderItem = factory.createOrderItemType();
		BookType book = factory.createBookType();
		book.setEan13(ean13);
		book.setTitle("Java in Action");
		orderItem.setBook(book);
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		try {
			XMLGregorianCalendar estimatedShippingDate = DatatypeFactory
					.newInstance().newXMLGregorianCalendar(cal);
			orderItem.setExpectedShippingDate(estimatedShippingDate);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		orderItem.setLineNumber(1);
		orderItem.setPrice(new BigDecimal(10));
		orderItem.setQuantityShipped(orderQuantity);
		
		OrderType order = factory.createOrderType();
		order.setOrderStatus(OrderStatusType.READY);
		order.getOrderItems().add(orderItem);
		
		response.setOrder(order);
		return response;
	}

}
