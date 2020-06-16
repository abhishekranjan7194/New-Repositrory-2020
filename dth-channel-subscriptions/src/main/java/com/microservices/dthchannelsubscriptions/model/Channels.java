package com.microservices.dthchannelsubscriptions.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CHANNELS")
public class Channels {
	@Id	
	@Column(name="CHANNEL_ID")
	private int channel_id;
	
	@Column(name="CHANNEL_NAME")
	private String channel_name;
	
	@Column(name="COST_PER_MONTH")
	private float cost_per_month;
	
	public Channels(){}

	public long getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(int channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public float getCost_per_month() {
		return cost_per_month;
	}

	public void setCost_per_month(float cost_per_month) {
		this.cost_per_month = cost_per_month;
	}
	
	
}
