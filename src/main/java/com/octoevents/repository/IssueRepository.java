package com.octoevents.repository;

import org.springframework.data.repository.CrudRepository;

import com.octoevents.entity.Issue;

public interface IssueRepository extends CrudRepository<Issue, Long>{

}
