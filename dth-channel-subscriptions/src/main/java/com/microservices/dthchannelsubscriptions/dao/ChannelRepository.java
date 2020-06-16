package com.microservices.dthchannelsubscriptions.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.dthchannelsubscriptions.model.Channels;

public interface ChannelRepository extends JpaRepository<Channels, Integer> {

}
