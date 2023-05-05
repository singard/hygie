package com.hygie.tasks;

import com.hygie.model.TaskClass;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CheckFreeSpaceDisc implements TaskClass {
	
	private String[] args;

	@Override
	public void executer() {
		// TODO Auto-generated method stub
		System.out.println("hello");
		System.out.println(args[0]);
	}

}
