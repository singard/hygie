package com.hygie.hygietask.tasks.httpserveur;

import com.hygie.hygietask.model.ResultTask;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class HTTPServerRunnable implements Runnable {

    private int port;
    private ResultTask resultTask;
    private CountDownLatch latch;

    public HTTPServerRunnable(int port, ResultTask resultTask) {
        this.port = port;
        this.resultTask = resultTask;
        this.latch = new CountDownLatch(1);
    }

    @Override
    public void run() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/ping", new PingHandler());
            server.setExecutor(null);
            server.start();
            System.out.println("Serveur http démarré sur le port : " + port);
            resultTask.setResult("Serveur http démarré sur le port : " + port);
            resultTask.setSuccessfulTest(true);
        } catch (IOException e) {
            resultTask.setSuccessfulTest(false);
            resultTask.setResult("Error starting http server on port : " + e.toString());
            System.err.println("Error starting http server on port : " + e);
        } finally {
            latch.countDown(); // Permet au thread principal de reprendre l'exécution
        }
    }
}

