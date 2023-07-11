package com.hygie;

import static com.hygie.hygiejson.filemanager.ReadFile.read;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.hygie.export.PdfCreator;
import com.hygie.hygiejson.filemanager.ReadFile;
import com.hygie.hygietask.model.ResultTask;

@ShellComponent
public class RunCommand {

	@ShellMethod("Run the specified file")
	public String run(@ShellOption(help = "File name json") String fileNameJson) {
		// Obtenir le chemin complet du fichier JSON
		String DEFAULT_PLAYBOOK_PATH = System.getProperty("user.dir");

		DEFAULT_PLAYBOOK_PATH = DEFAULT_PLAYBOOK_PATH.concat(File.separator)
				.concat("data")
				.concat(File.separator)
				.concat(fileNameJson).concat(".json");

		System.out.println(DEFAULT_PLAYBOOK_PATH.toString());

		try {

			System.out.println(DEFAULT_PLAYBOOK_PATH.toString());
			File file = new File(DEFAULT_PLAYBOOK_PATH);
			List<ResultTask> resultTasks = ReadFile.read(file);
			for (ResultTask task : resultTasks) {

				System.out.println(task);

			}

			//génération du raport
			Date currentDate = new Date();
			SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			String formattedDateTime = dateTimeFormat.format(currentDate);

			String title = fileNameJson+" report";
			String folder = "report";
			String fileNamePdf = folder+File.separator+fileNameJson+"_result_"+formattedDateTime+".pdf";

			PdfCreator pdfCreator = new PdfCreator(title, fileNamePdf);
			pdfCreator.createPdf(resultTasks);

		} catch (Exception e) {
			e.printStackTrace();
		}



		System.out.println("Appuyez sur une touche pour continuer...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine(); // Attend que l'utilisateur appuie sur Entrée
		System.out.println("Continuation du programme...");
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

