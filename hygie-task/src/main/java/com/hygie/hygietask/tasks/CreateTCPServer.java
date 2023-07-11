package com.hygie.hygietask.tasks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
  
        
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            log.info("The server is listening on the port" + port);
            resultTask.setSuccessfulTest(true);
            resultTask.setResult("The server is listening on the port "+ port);
            // thread pour la continuation du serveur tcp sans bloquer le programme principale
            // thread crée par une labda
            Thread serverThread = new Thread(() -> {
                // Boucle infinie pour accepter les connexions des clients
                while (true) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        log.info("New connection incoming: {}", clientSocket.getInetAddress().getHostAddress());
                    } catch (IOException e) {
                        log.error("Error accepting client connection: {}", e);
                    }
                }
            });
            serverThread.start();
            
        } catch (IOException e) {
            log.error("Error in create server in port {} ", port,e);
            resultTask.setSuccessfulTest(false);
            resultTask.setResult("Error in create server in port "+port+" "+e);
    
        }
        
        
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
