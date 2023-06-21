package com.hygie;

import java.io.File;
import java.nio.file.Paths;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class RunCommand {

    @ShellMethod("Run the specified file")
    public String run(@ShellOption(help = "File name") String fileName) {
    	String jarPath = getJarPath();
        String folderPath = Paths.get(jarPath).getParent().toString();
        String filePath = Paths.get(folderPath, fileName).toString();

        // Implémentez ici la logique pour exécuter le fichier spécifié
        return "Running file: " + filePath;
    }

    private String getJarPath() {
        String jarPath = "";
        try {
            jarPath = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        } catch (Exception e) {
            // Gérer les erreurs lors de la récupération du chemin du jar
            e.printStackTrace();
        }
        return jarPath;
    }
}

