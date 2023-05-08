package com.hygie;

import com.hygie.model.ExecuteTask;
import com.hygie.model.ResultTask;
import com.hygie.model.TaskType;
import com.hygie.research.ResearchTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main { 

	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		
		ExecuteTask executeTask = new ExecuteTask(TaskType.CHECK_FREE_SPACE_DISC,"free space up to 50 Go", new String[] {"50"} );
		ResultTask resultTask = ResearchTest.executerClasse(executeTask);
		log.info(resultTask.toString());
		
		ExecuteTask executeTask500 = new ExecuteTask(TaskType.CHECK_FREE_SPACE_DISC,"free space up to 500 Go", new String[] {"500"} );
		ResultTask resultTask500 = ResearchTest.executerClasse(executeTask500);
		log.info(resultTask500.toString());

	}
	
	

}
