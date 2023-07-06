package com.hygie.hygietask.tasks.httpserveur;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PingHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String response = "pong";
		exchange.sendResponseHeaders(200, response.length());
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
	
}