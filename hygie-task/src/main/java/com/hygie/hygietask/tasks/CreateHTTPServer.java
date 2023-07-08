package com.hygie.hygietask.tasks;

import com.hygie.hygietask.model.ResultTask;
import com.hygie.hygietask.model.TaskClass;
import com.hygie.hygietask.tasks.httpserveur.HTTPServerRunnable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateHTTPServer implements TaskClass {

    private String[] args;
    private ResultTask resultTask = new ResultTask();
    
    public CreateHTTPServer(String[] args) {
        this.args = args;
    }

    @Override
    public ResultTask executer() {
        verifyArgs();

        log.debug("Argument ajouté : {}", args[0]);

        int port = Integer.parseInt(args[0]);

        Thread serverThread = new Thread(new HTTPServerRunnable(port, resultTask));
        serverThread.start();

        try {
            serverThread.join(); // Met le thread principal en attente jusqu'à ce que le thread du serveur HTTP se termine
        } catch (InterruptedException e) {
            log.error("Le thread principal a été interrompu : {}", e.getMessage());
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
