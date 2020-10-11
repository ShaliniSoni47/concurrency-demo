package com.shalini.concurrent.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EndProcessStatus {

	NO_PROCESS_RUNNING("No process to terminate"),
	SUCCESSFULLY_TERMINATED("Process terminated Successfully");
	
	private final String msg;
}
