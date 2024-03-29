package com.hygie.hygietask;

import com.hygie.hygietask.model.ExecuteTask;
import com.hygie.hygietask.model.ResultTask;
import com.hygie.hygietask.model.TaskType;
import com.hygie.hygietask.research.ResearchTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main { 

	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		System.setProperty("crypto.policy", "unlimited");

		//		ExecuteTask executeTaskTcp = new ExecuteTask(TaskType.CREATE_TCP_SERVER,"tcp server in port 1234", new String[] {"1234"} );
		//		ResultTask resultTaskTcp = ResearchTest.executerClasse(executeTaskTcp);
		//		log.info(resultTaskTcp.toString());
		//		
		//		ExecuteTask executeTaskTcp2 = new ExecuteTask(TaskType.CREATE_TCP_SERVER,"tcp server in port 1234", new String[] {"1234"} );
		//		ResultTask resultTaskTcp2 = ResearchTest.executerClasse(executeTaskTcp2);
		//		log.info(resultTaskTcp2.toString());
		//
		//		
		//		ExecuteTask executeTask = new ExecuteTask(TaskType.CHECK_FREE_SPACE_DISC,"free space up to 50 Go", new String[] {"50"} );
		//		ResultTask resultTask = ResearchTest.executerClasse(executeTask);
		//		log.info(resultTask.toString());
		//		
		//		ExecuteTask executeTask500 = new ExecuteTask(TaskType.CHECK_FREE_SPACE_DISC,"free space up to 500 Go", new String[] {"500"} );
		//		ResultTask resultTask500 = ResearchTest.executerClasse(executeTask500);
		//		log.info(resultTask500.toString());
		//		
		//		ExecuteTask executeTaskRAM50 = new ExecuteTask(TaskType.CHECK_TOTAL_RAM,"RAM up to 50 Go", new String[] {"50"} );
		//		ResultTask resultTaskRAM50 = ResearchTest.executerClasse(executeTaskRAM50);
		//		log.info(resultTaskRAM50.toString());
		//		
		//		ExecuteTask executeTaskRAM5 = new ExecuteTask(TaskType.CHECK_TOTAL_RAM,"RAM up to 5 Go", new String[] {"5"} );
		//		ResultTask resultTaskRAM5 = ResearchTest.executerClasse(executeTaskRAM5);
		//		log.info(resultTaskRAM5.toString());

		ExecuteTask executeTaskHttpRequest2000n = new ExecuteTask(TaskType.HTTP_REQUEST,"ckeck if the port 2000 is connected", new String[] {"https","localhost","2000","3000"} );
		ResultTask resultTaskHttpRequest2000n = ResearchTest.executerClasse(executeTaskHttpRequest2000n);
		log.info(resultTaskHttpRequest2000n.toString());

		ExecuteTask executeTaskHttp2000 = new ExecuteTask(TaskType.CREATE_HTTP_SERVER,"ckeck if the port 2000 is free", new String[] {"2000"} );
		ResultTask resultTaskHttp2000 = ResearchTest.executerClasse(executeTaskHttp2000);
		log.info(resultTaskHttp2000.toString());

		//		ExecuteTask try2000 = new ExecuteTask(TaskType.HTTP_REQUEST,"ckeck if the port 2000 is connected in https (for server http)", new String[] {"https","localhost","2000"} );
		//		ResultTask try82000 = ResearchTest.executerClasse(try2000);
		//		log.info(try82000.toString());

		ExecuteTask trys2000 = new ExecuteTask(TaskType.HTTP_REQUEST,"ckeck if the port 2000 is connected in http (for server http)", new String[] {"http","localhost","2000","3000"} );
		ResultTask trys82000 = ResearchTest.executerClasse(trys2000);
		log.info(trys82000.toString());





		ExecuteTask executeTaskHttps443  = new ExecuteTask(TaskType.CREATE_HTTPS_SERVER,"ckeck if the port 2020 is free to https server", new String[] {"2020"} );
		ResultTask resultTaskHttps443  = ResearchTest.executerClasse(executeTaskHttps443 );
		log.info(resultTaskHttps443 .toString());
		
		ExecuteTask executeTaskHttpRequest2020n = new ExecuteTask(TaskType.HTTP_REQUEST,"ckeck if the port 2020 is connected", new String[] {"https","localhost","2020","3000"} );
		ResultTask resultTaskHttpRequest2020n = ResearchTest.executerClasse(executeTaskHttpRequest2020n);
		log.info(resultTaskHttpRequest2020n.toString());

	}



}
