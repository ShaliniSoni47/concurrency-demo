package com.shalini.concurrent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shalini.concurrent.enums.EndProcessStatus;
import com.shalini.concurrent.response.ResponseObject;
import com.shalini.concurrent.response.StartProcessResponse;
import com.shalini.concurrent.service.ConcurrentService;

import io.swagger.annotations.ApiOperation;

@RestController
public class ConcurrentController {
	
	
	private ConcurrentService concurrentService;
	
	ConcurrentController(ConcurrentService concurrentService){
		this.concurrentService = concurrentService;
	}

	
	/**
	 * Initiates the processing request on Shared data, invalidating the previous requests
	 * @return Modified value of shared data
	 */
	@ApiOperation(value = "API for starting process on shared data and invalidating the first request")
	@GetMapping("/start-process")
	public ResponseObject<StartProcessResponse> start() {
		return new ResponseObject<StartProcessResponse>(concurrentService.startProcess());
		  
	}
	
	/**
	 * Terminates process running on the Shared data
	 * @return Message of request completion
	 */
	@ApiOperation(value = "API for ending process on shared data")
	@GetMapping("/end-process")
	public ResponseObject<EndProcessStatus> end() {
		return  new ResponseObject<EndProcessStatus>(concurrentService.endProcess());
	}
	
}
