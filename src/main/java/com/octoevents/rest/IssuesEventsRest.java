package com.octoevents.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.octoevents.DTO.ResponseDTO;
import com.octoevents.Enum.Error;
import com.octoevents.entity.IssuesEvents;
import com.octoevents.errors.ResourcesNotFoundException;
import com.octoevents.services.IssuesEventsService;
import com.octoevents.util.MessengerUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@Api(value="OctoEvents Webhooks Gib Events")
public class IssuesEventsRest {

	@Autowired
	private IssuesEventsService issuesEventsService;
	
	@Autowired
	private MessengerUtil messengerUtil;
	
	@ApiOperation(value="Post Issues Events")
	@PostMapping(path = "/events")
	public IssuesEvents saveIssues(@RequestBody IssuesEvents event) {
		this.messengerUtil.veryfyObjectIsNull(event, Error.ERRO_POST.getDescricao());
		return this.issuesEventsService.save(event);
	}

	@ApiOperation(value="Return Action and Date Create")
	@RequestMapping(method = RequestMethod.GET, path = "/issues/{issueId}/events")
	public List<ResponseDTO> buscarPorNome(@PathVariable("issueId") Long issueId) {
		this.messengerUtil.verifyReturnFind(issueId, Error.ERRO_GET.getDescricao());
		List<ResponseDTO> responseDTOList = this.issuesEventsService.findByIssueId(issueId);
		return responseDTOList;
	}

	

}
