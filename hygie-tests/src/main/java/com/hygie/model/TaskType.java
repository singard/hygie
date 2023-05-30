package com.hygie.model;

import com.hygie.tasks.CheckFreeSpaceDisc;
import com.hygie.tasks.CheckTotalRam;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TaskType {
	
	CHECK_FREE_SPACE_DISC(CheckFreeSpaceDisc.class),
	CHECK_TOTAL_RAM(CheckTotalRam.class); 
	
	private final Class<? extends TaskClass> classe;

    public Class<? extends TaskClass> getClasse() {
        return classe;
    }

}
