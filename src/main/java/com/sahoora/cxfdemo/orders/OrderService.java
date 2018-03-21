package com.sahoora.cxfdemo.orders;

import com.sahoora.schema.order.OrderInquiryResponseType;

public interface OrderService {
	OrderInquiryResponseType processOrder(int uniqueOerderId,
			int orderQuantity,
			int accountId,
			long ean13);
}
