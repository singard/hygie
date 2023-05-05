package com.hygie.tasks;

import com.hygie.model.TaskClass;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CheckTotalRam implements TaskClass{
	
	private String[] args;

	@Override
	public void executer() {
		// TODO Auto-generated method stub
		
		System.out.println("hello 2");
		System.out.println(args[1]);
		
	}

}
