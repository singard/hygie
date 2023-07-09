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
        log.debug("Argument ajout� : {}", args[0]);
        int port = Integer.parseInt(args[0]);
        
        
		return resultTask;
	}

	@Override
	public void verifyArgs() {
		 if (args.length != 1) {
	            throw new IllegalArgumentException("Le tableau doit contenir exactement un �l�ment.");
	        }

	        try {
	            Integer.parseInt(args[0]);
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("L'�l�ment du tableau doit �tre un nombre entier.");
	        }
		
	}

}
