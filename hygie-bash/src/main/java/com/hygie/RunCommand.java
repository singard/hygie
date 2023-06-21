package com.hygie;

import java.io.File;
import java.nio.file.Paths;

import org.jline.utils.Log;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.hygie.research.ReadFile;

@ShellComponent
public class RunCommand {

    @ShellMethod("Run the specified file")
    public String run(@ShellOption(help = "File name") String fileName) {
    	String jarPath = getJarPath();
        String folderPath = Paths.get(jarPath).getParent().toString();
        
        String filePath = Paths.get(folderPath, fileName).toString();
        Log.info("Running file: " + filePath);
        // Impl�mentez ici la logique pour ex�cuter le fichier sp�cifi�

        ReadFile.read(new File("filePath"));
        //lecture du fichier et transformation en ExecuteTask
        
        //g�n�ration du raport
        return "Running file: " + filePath;
    }

    private String getJarPath() {
        String jarPath = "";
        try {
            jarPath = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        } catch (Exception e) {
            // G�rer les erreurs lors de la r�cup�ration du chemin du jar
            e.printStackTrace();
        }
        return jarPath;
    }
}

