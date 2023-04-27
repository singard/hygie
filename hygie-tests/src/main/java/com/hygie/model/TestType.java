package com.hygie.model;

import com.hygie.tests.CheckFreeSpaceDisc;
import com.hygie.tests.CheckTotalRam;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestType {
	
	CHECK_FREE_SPACE_DISC(CheckFreeSpaceDisc.class),
	CHECK_TOTAL_RAM(CheckTotalRam.class); 
	
	private final Class<? extends TestClass> classe;

    public Class<? extends TestClass> getClasse() {
        return classe;
    }

}
