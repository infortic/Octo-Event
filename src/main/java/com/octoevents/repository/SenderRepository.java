package com.octoevents.repository;

import org.springframework.data.repository.CrudRepository;

import com.octoevents.entity.Sender;

public interface SenderRepository extends CrudRepository<Sender, Long> {

}
