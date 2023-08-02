package com.hygie.hygietask.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

			int port =  Integer.parseInt(args[1]);
			String host= args[0];

			URL url = new URL("http://" + host + ":" + port + "/ping");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				log.info("Ping response from " + host + ":" + port + ": " + response.toString());
				resultTask.setSuccessfulTest(true);
			} else {
				resultTask.setSuccessfulTest(false);
				resultTask.setResult("Ping request failed. Response code: " + responseCode);
				log.info("Ping request failed. Response code: " + responseCode);
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
		if (args.length != 2) {
			throw new IllegalArgumentException("Le tableau doit contenir exactement deux éléments.");
		}

		try {
			// Vérification que le premier argument est une adresse IP valide
			InetAddress.getByName(args[0]);
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException("Le premier élément du tableau doit être une adresse IP valide.");
		}

		try {
			// Vérification que le deuxième argument est un entier valide pour le port
			int port = Integer.parseInt(args[1]);
			if (port <= 0 || port > 65535) {
				throw new IllegalArgumentException("Le deuxième élément du tableau doit être un entier valide pour le port (entre 1 et 65535).");
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Le deuxième élément du tableau doit être un entier valide pour le port.");
		}
	}

}


