package com.zipe.utils.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "Head")
public class Head {
	
	@XmlElement(name = "Invoice")
	public Customer customer;
	@XmlElement(name = "Client")
	public Clienter clienter;

	@XmlTransient
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@XmlTransient
	public Clienter getClienter() {
		return clienter;
	}

	public void setClienter(Clienter clienter) {
		this.clienter = clienter;
	}
	
	
}
