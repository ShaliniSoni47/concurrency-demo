package com.shalini.concurrent.service;

import com.shalini.concurrent.enums.EndProcessStatus;
import com.shalini.concurrent.response.StartProcessResponse;

public interface ConcurrentService {

	StartProcessResponse startProcess();
	
	EndProcessStatus endProcess();

}
