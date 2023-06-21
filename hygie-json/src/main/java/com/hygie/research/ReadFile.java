package com.hygie.research;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.hygie.model.ExecuteTask;
import com.hygie.model.ResultTask;
import com.hygie.model.TaskType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadFile {
	 public static void read(File file) {
	        ObjectMapper objectMapper = new ObjectMapper();
	        try {
	            List<ExecuteTask> executeTasks = objectMapper.readValue(file, new TypeReference<List<ExecuteTask>>() {});
	            // Faites ce que vous voulez avec la liste d'objets ExecuteTask
	            for (ExecuteTask task : executeTasks) {
	                System.out.println(task);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
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
