package com.microservices.dthchannelsubscriptions.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservices.dthchannelsubscriptions.model.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {
	
	@Query("select c from Customers c where c.subscriber_id=?1")
	Customers getCustomer(long subscriber_id);
}
