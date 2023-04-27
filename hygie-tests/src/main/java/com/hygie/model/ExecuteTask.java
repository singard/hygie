package com.hygie.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExecuteTask {
	
	private TaskType testType;
	private String description;
	private String[] args;

}
