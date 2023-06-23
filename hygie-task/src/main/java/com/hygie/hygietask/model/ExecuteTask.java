package com.hygie.hygietask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteTask {
	
	private TaskType testType;
	private String description;
	private String[] args;

}
