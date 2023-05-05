package com.hygie.research;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.hygie.model.ExecuteTask;
import com.hygie.model.TaskClass;
import com.hygie.model.TaskType;

public class ResearchTest {

	public static void executerClasse(ExecuteTask executeTask) {
		try {
		    TaskType taskType = executeTask.getTestType();

		    Class<? extends TaskClass> classe = taskType.getClasse();
		    Constructor<? extends TaskClass> constructor = classe.getDeclaredConstructor(String[].class);

		    String[] arguments = executeTask.getArgs();
		    TaskClass instance = constructor.newInstance((Object) arguments);
		    
		    instance.executer();
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}



		
	}


}
