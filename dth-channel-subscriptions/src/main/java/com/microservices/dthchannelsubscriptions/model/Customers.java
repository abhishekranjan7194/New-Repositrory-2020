package com.microservices.dthchannelsubscriptions.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
public class Customers {
	@Id
	@Column(name="SUBSCRIBER_ID")
	private long subscriber_id;
	
	@Column(name="REGISTERED_MOBILE")
	private long registered_mobile;
	
	@Column(name="FIRST_NAME")
	private String first_name;
	
	@Column(name="LAST_NAME")
	private String last_name;
	
	public Customers() {}

	public long getSubscriber_id() {
		return subscriber_id;
	}

	public void setSubscriber_id(long subscriber_id) {
		this.subscriber_id = subscriber_id;
	}

	public long getRegistered_mobile() {
		return registered_mobile;
	}

	public void setRegistered_mobile(long registered_mobile) {
		this.registered_mobile = registered_mobile;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
		
}
