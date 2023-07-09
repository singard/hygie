package com.hygie.hygietask.model;

import com.hygie.hygietask.tasks.CheckFreeSpaceDisc;
import com.hygie.hygietask.tasks.CheckTotalRam;
import com.hygie.hygietask.tasks.CreateHTTPSServer;
import com.hygie.hygietask.tasks.CreateHTTPServer;
import com.hygie.hygietask.tasks.CreateTCPServer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TaskType {
	
	CHECK_FREE_SPACE_DISC(CheckFreeSpaceDisc.class),
	CHECK_TOTAL_RAM(CheckTotalRam.class), 
	CREATE_HTTP_SERVER(CreateHTTPServer.class),
	CREATE_HTTPS_SERVER(CreateHTTPSServer.class),
	CREATE_TCP_SERVER(CreateTCPServer.class);
	
	private final Class<? extends TaskClass> classe;

    public Class<? extends TaskClass> getClasse() {
        return classe;
    }

}
