package com.microservices.dthchannelsubscriptions.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.dthchannelsubscriptions.dao.ChannelRepository;
import com.microservices.dthchannelsubscriptions.dao.SubscriptionChannelRepo;
import com.microservices.dthchannelsubscriptions.exceptions.InvalidChannelException;
import com.microservices.dthchannelsubscriptions.exceptions.InvalidCustomerException;
import com.microservices.dthchannelsubscriptions.model.Channels;
import com.microservices.dthchannelsubscriptions.model.Subscription_Channel;

@Service
public class SubscriptionChannelService {
	
	@Autowired
	private SubscriptionChannelRepo subchrepo;
	
	@Autowired
	private ChannelRepository channelRepo;
	
	// Subscribe to Channels
	public Subscription_Channel doSubscription(Subscription_Channel sub_ch) {
		int flag = 0;
		List<Channels> channelsList = channelRepo.findAll();
		for(Channels channel: channelsList) {
			if(channel.getChannel_id() == sub_ch.getChannel_id()) {
				flag = 1;
				break;
			}
		}
		
		if (flag == 0) {
			throw new InvalidChannelException("Channel Does Not Exist");
		}
		else
			return subchrepo.save(sub_ch);		
	}
	
	
	// Remove Channel Subscription for a Particular Subscriber
	public String removeSubscription(long subId, int chId) {
		int flag = 0;
		List<Subscription_Channel> totalList1 = subchrepo.getSubscriptionDetails();
		// List<Subscription_Channel> specificSubscriptionChannelList1 = new ArrayList<>();
		for(Subscription_Channel subCh : totalList1) {
			if(subCh.getSubscriber_id() == subId) {
				subchrepo.removeSubscription(subId, chId);
				flag = 1;
			}
		}
		if (flag == 0) {
			throw new InvalidCustomerException("SUBSCRIBER ID is INVALID!. PLEASE PROVIDE A VALID ONE");
		}
		else
			return "CHANNEL UNSUBSCRIBED SUCCESSFULLY";
			
	}
	
	// Get Total Cost for Channels Subscribed
	public Object getChannelCost(long subId) {
		List<Subscription_Channel> subscriptionChannel = subchrepo.getSubscriptionDet(subId);
		List<Channels> channelsList = channelRepo.findAll();
		
		float totalPrice = 0;
		for(Channels channel: channelsList) {
			for (Subscription_Channel subCh:subscriptionChannel) {
				if(channel.getChannel_id() == subCh.getChannel_id()) {
					totalPrice = totalPrice + channel.getCost_per_month();
				}
			}
		}
		return totalPrice + 100;		
		
	}
	
	
	//Get Channel List for a Subscriber
	public Object getChannelList(long subId) {
		List<Subscription_Channel> subChannel = subchrepo.getSubscriptionDet(subId);
		List<Channels> channelsList = channelRepo.findAll();
		List<Object> nameList = new ArrayList<>();
		for (Channels channel : channelsList) {
			for (Subscription_Channel subCh : subChannel) {
				if(channel.getChannel_id() == subCh.getChannel_id()) {
					nameList.add(channel.getChannel_name());
				}
			}
		}
		return nameList;
	}
	
}
