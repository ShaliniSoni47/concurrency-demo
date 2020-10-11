package com.shalini.concurrent.service;

import com.shalini.concurrent.vo.ResponseValueObject;

public interface ConcurrentService {

	ResponseValueObject startProcess();
	
	String endProcess();

}
