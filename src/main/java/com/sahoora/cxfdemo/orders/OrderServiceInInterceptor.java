package com.sahoora.cxfdemo.orders;

import org.w3c.dom.Element;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class OrderServiceInInterceptor extends AbstractSoapInterceptor {

	public OrderServiceInInterceptor() {
		super(Phase.USER_PROTOCOL);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		QName qname = new QName("http://www.sahoora.com/service/orders/", "userName");
		SoapHeader header = (SoapHeader)message.getHeader(qname);

		String userName  = ((Element)header.getObject()).getTextContent();
		System.err.println("User in inbound interceptor received : "+ userName);
	}
}
