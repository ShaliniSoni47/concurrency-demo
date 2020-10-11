package com.shalini.concurrent.service;

import com.shalini.concurrent.enums.StartProcessResponseEnums;
import com.shalini.concurrent.response.StartProcessResponse;

public interface ConcurrentService {

	StartProcessResponse startProcess();
	
	StartProcessResponseEnums endProcess();

}
