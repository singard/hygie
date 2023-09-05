package com.hygie.hygietask.tasks;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException; 

import com.hygie.hygietask.model.ResultTask;
import com.hygie.hygietask.model.TaskClass;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class HttpRequest implements TaskClass{

	private String[] args;


	@Override
	public ResultTask executer() {
		ResultTask resultTask = new ResultTask();
		try {
			String typeRequest = args[0];
			String host= args[1];
			int port =  Integer.parseInt(args[2]);
			int timeout = Integer.parseInt(args[3]);

			URL url = new URL(typeRequest+"://" + host + ":" + port + "/ping");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(timeout);
			connection.setConnectTimeout(timeout);

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {


				
				resultTask.setSuccessfulTest(true);
			} else {
				resultTask.setSuccessfulTest(false);
				resultTask.setResult("Ping request failed. Response code: " + responseCode);
				log.error("Ping request failed. Response code: " + responseCode);
				return resultTask;
			}
		} catch (Exception e) {
			resultTask.setSuccessfulTest(false);
			resultTask.setResult("Error while sending ping request: " + e.getMessage());
			log.error("Error while sending ping request: " + e.getMessage());
			return resultTask;
		}
		return resultTask;
	}

	@Override
	public void verifyArgs() {
		if (args.length != 4) {
			throw new IllegalArgumentException("Le tableau doit contenir exactement trois éléments.");
		}

		try {
			if (args[0].equals("http")||args[0].equals("https")) {

			} else {
				throw new IllegalArgumentException("Le premier élément du tableau doit valoir http ou https.");
			}
		} catch (Exception e) {
			log.error("Error: ", e);
		}
		try {
			// Vérification que le premier argument est une adresse IP valide
			InetAddress.getByName(args[1]);
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException("Le second élément du tableau doit être une adresse IP valide.");
		}

		try {
			// Vérification que le deuxième argument est un entier valide pour le port
			int port = Integer.parseInt(args[2]);
			if (port <= 0 || port > 65535) {
				throw new IllegalArgumentException("Le troisième élément du tableau doit être un entier valide pour le port (entre 1 et 65535).");
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Le troisième élément du tableau doit être un entier valide pour le port.");
		}
	}

}


