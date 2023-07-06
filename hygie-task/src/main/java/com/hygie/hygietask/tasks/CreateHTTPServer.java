package com.hygie.hygietask.tasks;

import com.hygie.hygietask.model.ResultTask;
import com.hygie.hygietask.model.TaskClass;
import com.hygie.hygietask.tasks.httpserveur.PingHandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@Slf4j
@AllArgsConstructor
public class CreateHTTPServer implements TaskClass{

	private String[] args;
	
	@Override
	public ResultTask executer() {
		ResultTask resultTask = new ResultTask();
		verifyArgs();

		log.debug("Argument ajouté : {}", args[0]);

		int port = Integer.parseInt(args[0]);


		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/ping", new PingHandler());
			server.setExecutor(null);
			server.start();
			System.out.println("Serveur http démarré sur le port : " + port);
			resultTask.setSuccessfulTest(true);
		} catch (IOException e) {
			resultTask.setSuccessfulTest(false);
			System.err.println("Erreur lors du démarrage du serveur : " + e);
		}
		resultTask.setResult("Serveur http démarré sur le port : " + port);

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





