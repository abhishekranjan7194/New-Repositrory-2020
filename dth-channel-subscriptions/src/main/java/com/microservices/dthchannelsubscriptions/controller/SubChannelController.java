package com.microservices.dthchannelsubscriptions.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.dthchannelsubscriptions.dao.SubscriptionChannelRepo;
import com.microservices.dthchannelsubscriptions.exceptions.InvalidCustomerException;
import com.microservices.dthchannelsubscriptions.dao.ChannelRepository;
import com.microservices.dthchannelsubscriptions.dao.CustomersRepository;
import com.microservices.dthchannelsubscriptions.model.Channels;
import com.microservices.dthchannelsubscriptions.model.Customers;
import com.microservices.dthchannelsubscriptions.model.Subscription_Channel;
import com.microservices.dthchannelsubscriptions.service.SubscriptionChannelService;

@RestController
public class SubChannelController {

	@Autowired
	private SubscriptionChannelRepo subchrepo;
	
	@Autowired
	private CustomersRepository customersRepo;
	
	@Autowired
	private ChannelRepository channelRepo;
	
	@Autowired
	private SubscriptionChannelService subChannelService;
	
	@PostMapping("/subscription")
	public ResponseEntity<String> doSubscription(@RequestBody Subscription_Channel sub_channel){
		if(subchrepo.subscriberIdCheck(sub_channel.getSubscriber_id()) == 0) {
			throw new InvalidCustomerException("Subscriber ID is not a valid One!");
		}		
		else
		{
			subChannelService.doSubscription(sub_channel);
			return new ResponseEntity<String>("Channel has been Subscribe Successfully!", HttpStatus.CREATED);
		}
	}
	

	@DeleteMapping(value="/removeSubscription/{sub_id}/{channel_id}")
	public ResponseEntity<Object> removeSubscription(@PathVariable long sub_id, @PathVariable int channel_id){
		subChannelService.removeSubscription(sub_id, channel_id);
		return new ResponseEntity<Object>("Channel Unsubscribed Successfully !", HttpStatus.OK);
	}

	@GetMapping(path="/price/{subscriberId}")
	public ResponseEntity<Object> getPrice(@PathVariable long subscriberId){
		return new ResponseEntity<Object>(subChannelService.getChannelCost(subscriberId), HttpStatus.OK);
	}
	
	@GetMapping("/channelList/{subId}")
	public ResponseEntity<Object> getChannelList(@PathVariable long subId) {
		return new ResponseEntity<Object>(subChannelService.getChannelList(subId), HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public List<Channels> testClass() {
	List<Channels> channelsList = channelRepo.findAll();
	return channelsList;
	}
	
	
	@GetMapping("/getAllSubscriptions/{subId}")
	public ResponseEntity<Object> getSubsriptionDetails(@PathVariable long subId) {
		if (subchrepo.subscriberIdCheck(subId) == 0) {
			throw new InvalidCustomerException("Invalid Subscriber Id !");
		}
		else
		{
			Map<String, Object> subDetailsMap = new HashMap<String, Object>();
			Customers customers = customersRepo.getCustomer(subId);
			String customerName = customers.getFirst_name();
			float price = (float) subChannelService.getChannelCost(subId);
			Object nameList = subChannelService.getChannelList(subId);
			subDetailsMap.put("SubscriberId", subId);
			subDetailsMap.put("CustomerName", customerName);
			subDetailsMap.put("Total Subscription Cost", price);
			subDetailsMap.put("Channels Subscribed", nameList);
			
			return new ResponseEntity<>(subDetailsMap, HttpStatus.OK);
		}
	}
}	
