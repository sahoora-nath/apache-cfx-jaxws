package com.sahoora.cxfdemo.orders;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.sahoora.schema.order.OrderInquiryResponseType;
import com.sahoora.schema.order.OrderInquiryType;
import com.sahoora.service.orders.Orders;

@WebService(portName="ordersSOAP", serviceName="orders",
endpointInterface="com.sahoora.service.orders.Orders",
targetNamespace="http://www.sahoora.com/service/orders/")
public class DefaultOrdersEndpoint implements Orders {

	@Autowired
	private OrderService orderService;
	
	@Override
	public OrderInquiryResponseType processOrderPlacement(OrderInquiryType orderInquiry) {
		return orderService.processOrder(orderInquiry.getUniqueOrderId(), 
				orderInquiry.getOrderQuantity(), orderInquiry.getAccountId(),
				orderInquiry.getEan13());
	}

}
