package com.hygie.hygietask.research;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.hygie.hygietask.model.ExecuteTask;
import com.hygie.hygietask.model.ResultTask;
import com.hygie.hygietask.model.TaskClass;
import com.hygie.hygietask.model.TaskType;

public class ResearchTest {

	public static ResultTask executerClasse(ExecuteTask executeTask) {
		try {
		    TaskType taskType = executeTask.getTestType();

		    Class<? extends TaskClass> classe = taskType.getClasse();
		    Constructor<? extends TaskClass> constructor = classe.getDeclaredConstructor(String[].class);

		    String[] arguments = executeTask.getArgs();
		    TaskClass instance = constructor.newInstance((Object) arguments);
		    
		    ResultTask resultTask = instance.executer();
		    resultTask.setExecuteTask(executeTask);
		    return  resultTask;
		    
		} catch (Exception e) {
		    e.printStackTrace();
		    return null;
		}
		



		
	}


}
