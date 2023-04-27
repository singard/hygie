package com.hygie;

import com.hygie.model.ExecuteTask;
import com.hygie.model.TaskClass;
import com.hygie.model.TaskType;

public class Main {

	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		// TODO Auto-generated method stub
		
		ExecuteTask executeTask = new ExecuteTask(TaskType.CHECK_TOTAL_RAM,"free space up to 50 Go", new String[0] );
		
		TaskType classe = executeTask.getTestType();
		executerClasse(classe);

	}
	
	public static void executerClasse(TaskType taskType) throws IllegalAccessException, InstantiationException {
	    Class<? extends TaskClass> classe = taskType.getClasse();
	    TaskClass instance = classe.newInstance();
	    instance.executer();
	}

}
