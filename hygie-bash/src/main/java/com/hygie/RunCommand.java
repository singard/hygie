package com.hygie;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import org.jline.utils.Log;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.hygie.hygiejson.filemanager.ReadFile;

@ShellComponent
public class RunCommand {

    @ShellMethod("Run the specified file")
    public String run(@ShellOption(help = "File name") String fileName) {
    	
//    	String jarPath = getJarPath();
//        String folderPath = Paths.get(jarPath).getParent().toString();
//        
//        String filePath = Paths.get(folderPath, fileName).toString();
//        Log.info("Running file: " + filePath);
    	
    	 // Obtenir le chemin complet du fichier JSON
        ClassLoader classLoader = RunCommand.class.getClassLoader();
        URL resource = classLoader.getResource("data/"+fileName+".json");
        System.out.println(classLoader.getResource("data/"+fileName+".json").toString());
        if (resource != null) {
            String filePath = resource.getPath();
            System.out.println("Chemin complet du fichier : " + filePath);
            File file = new File(filePath);

            // Utiliser le fichier dans subproject2
            // Par exemple, utiliser MyClass de subproject1
         
            ReadFile.read(file);
            // ...
        } else {
            System.out.println("Fichier introuvable !");
        }
        
        // Implémentez ici la logique pour exécuter le fichier spécifié

       
        //lecture du fichier et transformation en ExecuteTask
        
        //génération du raport
        return "Running file: " ;
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

