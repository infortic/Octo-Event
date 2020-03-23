package com.octoevents.repository;

import org.springframework.data.repository.CrudRepository;

import com.octoevents.entity.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
