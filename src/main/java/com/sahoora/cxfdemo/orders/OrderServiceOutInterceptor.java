package com.sahoora.cxfdemo.orders;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.SoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.Phase;

public class OrderServiceOutInterceptor extends AbstractSoapInterceptor {

	public OrderServiceOutInterceptor() {
		super(Phase.WRITE);
		this.addBefore(SoapInterceptor.class.getName());
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		QName qname = new QName("http://www.sahoora.com/service/orders/", "userName");
		String userName = "bob";
		
		try {
			SoapHeader header = new SoapHeader(qname, userName, 
					new JAXBDataBinding(userName.getClass()));
			message.getHeaders().add(header);
		} catch(Exception e) {
			throw new Fault(e);
		}
	}

}
