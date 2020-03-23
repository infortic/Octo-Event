package com.octoevents;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.octoevents.entity.Issue;
import com.octoevents.entity.IssuesEvents;
import com.octoevents.services.IssuesEventsService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class OctoeventsApplicationTests {

	@Autowired
	private IssuesEventsService issuesEventsService;


	@Test
	void contextLoads() {

	}

	Long id = new Long(2);
	Date date = new Date();

	@Test
	public void saveTest() {

		Issue issue = new Issue();

		IssuesEvents eventTemp = new IssuesEvents();
		issue.setCreated_at(date);
		issue.setId(id);
		eventTemp.setIssuesId(issue.getId());
		eventTemp.setAction("Open");
		eventTemp.setIssue(issue);
		this.issuesEventsService.save(eventTemp);
		Assertions.assertThat(eventTemp.getIssuesId()).isNotNull();
		Assertions.assertThat(eventTemp.getAction()).isNotNull();
		Assertions.assertThat(issue.getCreated_at()).isNotNull();
	}

	@Test
	public void findIdIssueTest() {
		Issue issue = new Issue();
		IssuesEvents eventTemp = new IssuesEvents();
		issue.setCreated_at(date);
		issue.setId(id);
		eventTemp.setIssuesId(issue.getId());
		eventTemp.setAction("Open");
		eventTemp.setIssue(issue);
		this.issuesEventsService.save(eventTemp);
		IssuesEvents response = this.issuesEventsService.findByIdpk(issue.getId());
		Assertions.assertThat(eventTemp.getIssuesId()).isNotNull();
		Assertions.assertThat(eventTemp.getAction()).isNotNull();
		Assertions.assertThat(eventTemp.getIssue().getCreated_at()).isNotNull();
	}

}
