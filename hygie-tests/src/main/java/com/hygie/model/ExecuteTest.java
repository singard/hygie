package com.hygie.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExecuteTest {
	
	private TestType testType;
	private String description;
	private String[] args;

}
