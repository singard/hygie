package com.hygie;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Paths;

import org.jline.utils.Log;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

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
		String DEFAULT_PLAYBOOK_PATH = System.getProperty("user.dir");

		DEFAULT_PLAYBOOK_PATH = DEFAULT_PLAYBOOK_PATH.concat(File.separator)
				.concat("data")
				.concat(File.separator)
				.concat(fileName).concat(".json");

		System.out.println(DEFAULT_PLAYBOOK_PATH.toString());


		//    	 String jarPath = RunCommand.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		try {
			//        	 String decodedPath = URLDecoder.decode(jarPath, StandardCharsets.UTF_8.toString());
			//             URL jarURL = new URL("file", null, decodedPath);
			//             String parentPath = new URL(jarURL, ".").getPath();
			//             String dataFilePath = parentPath + "/data/"+fileName+".json";
//			URL dataURL = new URL(DEFAULT_PLAYBOOK_PATH);
			System.out.println(DEFAULT_PLAYBOOK_PATH.toString());

			//        if (dataURL != null) {
			//            String filePath = dataURL.getPath();
			//            System.out.println("Chemin complet du fichier : " + filePath);
			File file = new File(DEFAULT_PLAYBOOK_PATH);

			// Utiliser le fichier dans subproject2
			// Par exemple, utiliser MyClass de subproject1

			ReadFile.read(file);
			// ...
			//        } else {
			//            System.out.println("Fichier introuvable !");
			//        }
		} catch (Exception e) {
			e.printStackTrace();
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

