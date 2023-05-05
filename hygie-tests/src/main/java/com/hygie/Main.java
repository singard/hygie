package com.hygie;

import com.hygie.model.ExecuteTask;
import com.hygie.model.TaskClass;
import com.hygie.model.TaskType;
import com.hygie.research.ResearchTest;

public class Main {

	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		// TODO Auto-generated method stub
		
		ExecuteTask executeTask = new ExecuteTask(TaskType.CHECK_TOTAL_RAM,"free space up to 50 Go", new String[] {"argument1", "argument2"} );
		
		ResearchTest.executerClasse(executeTask);

	}
	
	

}
