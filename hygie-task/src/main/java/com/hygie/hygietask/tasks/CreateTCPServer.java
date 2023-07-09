package com.hygie.hygietask.tasks;

import com.hygie.hygietask.model.ResultTask;
import com.hygie.hygietask.model.TaskClass;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@AllArgsConstructor
public class CreateTCPServer implements TaskClass {
	
	private String[] args;

	@Override
	public ResultTask executer() {
        ResultTask resultTask = new ResultTask();
        verifyArgs();
        log.debug("Argument ajouté : {}", args[0]);
        int port = Integer.parseInt(args[0]);
        
        
		return resultTask;
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
