package com.shalini.concurrent.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

import com.shalini.concurrent.enums.ResponseEnums;
import com.shalini.concurrent.service.ConcurrentService;
import com.shalini.concurrent.vo.ResponseValueObject;

@ExtendWith(SpringExtension.class)
public class ConcurrentServiceImplTest {
	
	//@Autowired
	ConcurrentService concurrentService = new ConcurrentServiceImpl();
	
	ExecutorService pool;
	@BeforeEach
	public void initializeThreadPool() {
	      pool = Executors.newFixedThreadPool(1);
	}
	
	@Test
	public void endProcessNoPoolTest() {
		shutDownPool();
		assertEquals(ResponseEnums.NO_PROCESS_RUNNING.getMsg(),concurrentService.endProcess());
		
	}

	@Test
	public void endProcess() {
		assertEquals(ResponseEnums.SUCCESSFULLY_TERMINATED.getMsg(),concurrentService.endProcess());
	}
	
	@Test
	public void startProcess() {
		assertEquals(5, concurrentService.startProcess().getValue());
	}
	
	private void shutDownPool() {
		pool.shutdown();
	}
	
}
