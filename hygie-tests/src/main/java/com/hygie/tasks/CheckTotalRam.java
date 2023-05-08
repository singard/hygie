package com.hygie.tasks;

import com.hygie.model.ResultTask;
import com.hygie.model.TaskClass;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CheckTotalRam implements TaskClass{
	
	private String[] args;

	@Override
	public ResultTask executer() {
		
		verifyArgs();
		
		System.out.println("hello 2");
		System.out.println(args[1]);
		return null;
		
	}

	@Override
	public void verifyArgs() {

		if (args.length != 1) {
		    throw new IllegalArgumentException("Le tableau doit contenir exactement un élément.");
		}

		try {
		    Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
		    throw new IllegalArgumentException("L'élément du tableau doit être un nombre entier.");
		}		
	}
	
	

}
