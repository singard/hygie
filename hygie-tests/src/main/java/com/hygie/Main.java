package com.hygie;

import com.hygie.model.ExecuteTask;
import com.hygie.model.ResultTask;
import com.hygie.model.TaskType;
import com.hygie.research.ResearchTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main { 

	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		
//		ExecuteTask executeTask = new ExecuteTask(TaskType.CHECK_FREE_SPACE_DISC,"free space up to 50 Go", new String[] {"50"} );
//		ResultTask resultTask = ResearchTest.executerClasse(executeTask);
//		log.info(resultTask.toString());
//		
//		ExecuteTask executeTask500 = new ExecuteTask(TaskType.CHECK_FREE_SPACE_DISC,"free space up to 500 Go", new String[] {"500"} );
//		ResultTask resultTask500 = ResearchTest.executerClasse(executeTask500);
//		log.info(resultTask500.toString());
		
		ExecuteTask executeTaskRAM50 = new ExecuteTask(TaskType.CHECK_TOTAL_RAM,"RAM up to 50 Go", new String[] {"50"} );
		ResultTask resultTaskRAM50 = ResearchTest.executerClasse(executeTaskRAM50);
		log.info(resultTaskRAM50.toString());
		
		ExecuteTask executeTaskRAM5 = new ExecuteTask(TaskType.CHECK_TOTAL_RAM,"RAM up to 5 Go", new String[] {"5"} );
		ResultTask resultTaskRAM5 = ResearchTest.executerClasse(executeTaskRAM5);
		log.info(resultTaskRAM5.toString());
		
	}
	
	

}
