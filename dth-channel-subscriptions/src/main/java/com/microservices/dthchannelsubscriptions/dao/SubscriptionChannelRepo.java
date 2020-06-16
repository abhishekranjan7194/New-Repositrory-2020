package com.microservices.dthchannelsubscriptions.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.dthchannelsubscriptions.model.Subscription_Channel;

@Repository
public interface SubscriptionChannelRepo extends JpaRepository<Subscription_Channel, Integer> {
	
	@Query("select s from Subscription_Channel s")
	List<Subscription_Channel> getSubscriptionDetails();
	
	
	@Query("select count(subscriber_id) from Subscription_Channel s where s.subscriber_id=?1")
	int subscriberIdCheck(long subscriber_id);
	
	@Query("select s from Subscription_Channel s where s.subscriber_id=?1")
	List<Subscription_Channel> getSubscriptionDet(long subscriber_id);
	
	@Transactional
	@Modifying
	@Query("delete from Subscription_Channel s where s.subscriber_id=?1 and s.channel_id=?2")
	void removeSubscription(long subscriber_id, int channel_id);
	
	
}
