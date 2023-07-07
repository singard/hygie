package com.hygie;

import static com.hygie.hygiejson.filemanager.ReadFile.read;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import org.jline.utils.Log;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import com.hygie.hygiejson.filemanager.ReadFile;
import com.hygie.hygietask.model.ResultTask;

@ShellComponent
public class RunCommand {

	@ShellMethod("Run the specified file")
	public String run(@ShellOption(help = "File name") String fileName) {
		// Obtenir le chemin complet du fichier JSON
		String DEFAULT_PLAYBOOK_PATH = System.getProperty("user.dir");

		DEFAULT_PLAYBOOK_PATH = DEFAULT_PLAYBOOK_PATH.concat(File.separator)
				.concat("data")
				.concat(File.separator)
				.concat(fileName).concat(".json");

		System.out.println(DEFAULT_PLAYBOOK_PATH.toString());
		
		try {

			System.out.println(DEFAULT_PLAYBOOK_PATH.toString());
			File file = new File(DEFAULT_PLAYBOOK_PATH);
			List<ResultTask> resultTasks = ReadFile.read(file);
	        for (ResultTask task : resultTasks) {
	            
	            System.out.println(task);
	    		
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}

		//g�n�ration du raport
        System.out.println("Appuyez sur une touche pour continuer...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Attend que l'utilisateur appuie sur Entr�e
        System.out.println("Continuation du programme...");
		return "Running file: " ;
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

