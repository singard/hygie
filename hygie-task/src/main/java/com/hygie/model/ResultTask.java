package com.hygie.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultTask {
	
	private ExecuteTask executeTask;
	private boolean successfulTest;
	private String result;
	

}
