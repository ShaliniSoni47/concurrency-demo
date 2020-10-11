package com.shalini.concurrent.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.shalini.concurrent.dao.Shared;
import com.shalini.concurrent.enums.ApplicationErrors;
import com.shalini.concurrent.enums.EndProcessStatus;
import com.shalini.concurrent.exception.custom.ApplicationException;
import com.shalini.concurrent.response.StartProcessResponse;
import com.shalini.concurrent.service.ConcurrentService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ConcurrentServiceImpl implements ConcurrentService{
	
	private ExecutorService pool;
	
	public ConcurrentServiceImpl() {
		this.pool = createThreadPool(1);
	}
	

	private ExecutorService createThreadPool(int size) {
		return Executors.newFixedThreadPool(size);
	}

	
	/** 
	 * (non-Javadoc)
	 * @see com.shalini.concurrent.controller.ConcurrentController#end()
	 */
	@Override
	public EndProcessStatus endProcess() {
		
		if(pool.isShutdown()) {
			return EndProcessStatus.NO_PROCESS_RUNNING;
		}
		
		pool.shutdownNow();
		
		if(pool.isShutdown()){
			log.info("Shutdown Successfull");
			return EndProcessStatus.SUCCESSFULLY_TERMINATED;
		}else{
			throw new ApplicationException(ApplicationErrors.PROCCESSING_ERROR);
		}
		
		
	}

	
	
	/** 
	 * (non-Javadoc)
	 * @see com.shalini.concurrent.controller.ConcurrentController#start()
	 */
	@Override
	public StartProcessResponse startProcess() {
		
		if(!pool.isTerminated()){
		    log.info("Invalidated running process");
			endProcess();
		}
		try {
			
			 if(pool.isShutdown()) {
				 log.info("Reintializing Thread Pool");
				 pool = createThreadPool(1);
			 }
			 		 			 
			 pool.submit(() -> {
						 if(!Thread.interrupted()) {
							 try {
								TimeUnit.SECONDS.sleep(5);
							} catch (InterruptedException e) {	
								log.error("Thread Interuptted - " + e.getMessage());
							}
							 Shared.addToCount(5);	 
							 Thread.yield();
						 }
						    log.info("Thread Stopped");					 
			             });
		}catch(RejectedExecutionException e) {
			log.error("Thread execution Rejected - "+ e.getMessage());
		}
		pool.shutdown();
		return new StartProcessResponse(Shared.getCount());
	}

}
