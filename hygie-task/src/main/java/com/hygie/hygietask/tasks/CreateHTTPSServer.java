package com.hygie.hygietask.tasks;

import com.hygie.hygietask.model.ResultTask;
import com.hygie.hygietask.model.TaskClass;
import com.hygie.hygietask.tasks.httpserveur.PingHandler;

import javax.net.ssl.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.security.*;
import java.security.cert.CertificateException;

import com.sun.net.httpserver.HttpsServer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.sun.net.httpserver.HttpsConfigurator;

@Slf4j
@AllArgsConstructor
public class CreateHTTPSServer implements TaskClass {
	
	private String[] args;
	
	private static final String CERTIFICATE_NAME = "keystore";
	private static final char[] CERTIFICATE_PASSWORD = "certificate".toCharArray(); 
    


	@Override
	public ResultTask executer() {

    String DEFAULT_CERTIFICATE_PATH = System.getProperty("user.dir");
    DEFAULT_CERTIFICATE_PATH = DEFAULT_CERTIFICATE_PATH.concat(File.separator)
			.concat("certificate")
			.concat(File.separator)
			.concat(CERTIFICATE_NAME).concat(".jks");
    
    ResultTask resultTask = new ResultTask();
	verifyArgs();
	log.debug("Argument ajouté : {}", args[0]);
	int port = Integer.parseInt(args[0]);
	
	System.setProperty("crypto.policy", "unlimited");


    
		 try {
	            HttpsServer server = HttpsServer.create(new InetSocketAddress(port), 0);
	            SSLContext sslContext = SSLContext.getInstance("TLS");
	            KeyStore keyStore = KeyStore.getInstance("JKS");
	            keyStore.load(new FileInputStream(DEFAULT_CERTIFICATE_PATH), CERTIFICATE_PASSWORD);
	            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
	            keyManagerFactory.init(keyStore, CERTIFICATE_PASSWORD);
	            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
	            trustManagerFactory.init(keyStore);
	            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
	            server.setHttpsConfigurator(new HttpsConfigurator(sslContext));
	            server.createContext("/ping", new PingHandler());
	            server.setExecutor(null);
	            server.start();
	            log.info("Serveur HTTPS démarré sur le port : " + port);
	            resultTask.setSuccessfulTest(true);
	            resultTask.setResult("Serveur https démarré sur le port : " + port);
	        } catch (IOException | KeyStoreException | CertificateException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException e) {
	            log.error("Erreur lors du démarrage du serveur https : ", e);
	            resultTask.setSuccessfulTest(false);
	            resultTask.setResult("Erreur lors du démarrage du serveur https : " + e);
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


