package com.shalini.concurrent.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.shalini.concurrent.enums.EndProcessStatus;
import com.shalini.concurrent.service.ConcurrentService;

@ExtendWith(SpringExtension.class)
public class ConcurrentServiceImplTest {
	
	private ConcurrentService concurrentService;
	
	@BeforeEach
	public void setup() {
		concurrentService = new ConcurrentServiceImpl();
	}
	
	@Test
	public void endProcessNoPoolTest() {
		ExecutorService pool = (ExecutorService) ReflectionTestUtils.getField(concurrentService,"pool");	
		pool.shutdown();
		assertEquals(EndProcessStatus.NO_PROCESS_RUNNING,concurrentService.endProcess());
		
	}

	@Test
	public void endProcess() {
		assertEquals(EndProcessStatus.SUCCESSFULLY_TERMINATED,concurrentService.endProcess());
	}
	
	@Test
	public void startProcess() {
		assertEquals(5, concurrentService.startProcess().getCount());
	}
	
	
}
