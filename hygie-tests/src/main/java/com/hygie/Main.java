package com.hygie;

import java.lang.reflect.Executable;

import com.hygie.model.TaskClass;
import com.hygie.model.TaskType;

public class Main {

	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		// TODO Auto-generated method stub
		
		TaskType classe = TaskType.CHECK_TOTAL_RAM;
		executerClasse(classe);

	}
	
	public static void executerClasse(TaskType taskType) throws IllegalAccessException, InstantiationException {
	    Class<? extends TaskClass> classe = taskType.getClasse();
	    TaskClass instance = classe.newInstance();
	    instance.executer();
	}

}
