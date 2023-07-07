package com.hygie.hygiejson.filemanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hygie.hygietask.model.ExecuteTask;
import com.hygie.hygietask.model.ResultTask;
import com.hygie.hygietask.research.ResearchTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadFile {
	 public static List<ResultTask> read(File file) {
	        ObjectMapper objectMapper = new ObjectMapper();
	        List<ResultTask> resultTasks = new ArrayList<ResultTask>();
	        try {
	            List<ExecuteTask> executeTasks = objectMapper.readValue(file, new TypeReference<List<ExecuteTask>>() {});

	            for (ExecuteTask task : executeTasks) {
	            
	                System.out.println(task);
	            	ResultTask resultTask = ResearchTest.executerClasse(task);
	        		log.info(resultTask.toString());
	        		resultTasks.add(resultTask);
	        		
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return resultTasks;
	    }

    public static void createJsonFile(String fileName, List<ExecuteTask> taskList) {
        try {
            // Créer un objet ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Activer la mise en forme du JSON pour une meilleure lisibilité
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            // Convertir la liste de tâches en JSON et écrire dans le fichier
            objectMapper.writeValue(new File(fileName), taskList);

            System.out.println("Le fichier JSON '" + fileName + "' a été créé avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
