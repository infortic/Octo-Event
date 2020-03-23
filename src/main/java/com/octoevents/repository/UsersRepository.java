package com.octoevents.repository;

import org.springframework.data.repository.CrudRepository;

import com.octoevents.entity.Users;

public interface UsersRepository extends CrudRepository<Users, Long> {

}
