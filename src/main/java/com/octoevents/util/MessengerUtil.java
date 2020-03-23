package com.octoevents.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.octoevents.errors.ResourcesNotFoundException;
import com.octoevents.services.IssuesEventsService;


@Service
public class MessengerUtil {
	
	@Autowired
	private IssuesEventsService issuesEventsService;
	
	
	
	public void verifyReturnFind(Long id, String mgs) {
		if (issuesEventsService.findByIssueId(id).isEmpty())
			throw new ResourcesNotFoundException(mgs + " " + id);
	}
	
	public void veryfyObjectIsNull(Object object, String mgs) {
		if (object == null)
			throw new ResourcesNotFoundException(mgs);
	}
	
}
