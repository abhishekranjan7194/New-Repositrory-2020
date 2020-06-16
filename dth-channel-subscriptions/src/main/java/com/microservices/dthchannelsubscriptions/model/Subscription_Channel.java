package com.microservices.dthchannelsubscriptions.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Subscription_Channel")
public class Subscription_Channel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="Id")
	private Integer id;
	
	@Column(name="Subscription_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull(message = "Subscription Date is required")
	private Date subscription_date;
	
	@Column(name="Channel_ID")
	@NotNull(message = "Channel ID is required")	
	private int channel_id;
	
	@Column(name="Subscriber_ID")
	@NotNull(message = "Subscriber ID is required")	
	private long subscriber_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getSubscription_date() {
		return subscription_date;
	}

	public void setSubscription_date(Date subscription_date) {
		this.subscription_date = subscription_date;
	}

	public int getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(int channel_id) {
		this.channel_id = channel_id;
	}

	public long getSubscriber_id() {
		return subscriber_id;
	}

	public void setSubscriber_id(long subscriber_id) {
		this.subscriber_id = subscriber_id;
	}
	
}
