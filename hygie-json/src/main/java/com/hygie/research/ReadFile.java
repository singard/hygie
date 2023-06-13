package com.hygie.research;

import java.io.File;
import java.io.FileReader;

import com.hygie.model.ExecuteTask;
import com.hygie.model.ResultTask;
import com.hygie.model.TaskType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadFile {
    public static void read(File file) {
        try {
            // Créer un parser JSON
            JSONParser parser = new JSONParser();

            // Lire le fichier JSON
            Object obj = parser.parse(new FileReader(file));

            // Convertir en tableau JSON
            JSONArray jsonArray = (JSONArray) obj;

            // Parcourir les objets du tableau
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;

                // Récupérer les valeurs de testType, description et args
                String testType = (String) jsonObject.get("testType");
                String description = (String) jsonObject.get("description");
                JSONArray argsArray = (JSONArray) jsonObject.get("args");

                // Convertir les arguments en tableau de chaînes
                String[] args = new String[argsArray.size()];
                for (int i = 0; i < argsArray.size(); i++) {
                    args[i] = (String) argsArray.get(i);
                }

                // Vérifier le type de test et exécuter la tâche correspondante
                if (testType.equals("CHECK_TOTAL_RAM")) {
                    ExecuteTask executeTask = new ExecuteTask(TaskType.CHECK_TOTAL_RAM, description, args);
                    ResultTask resultTask = ResearchTest.executerClasse(executeTask);
                    log.info(resultTask.toString());
                } else if (testType.equals("CHECK_FREE_SPACE_DISC")) {
                    ExecuteTask executeTask = new ExecuteTask(TaskType.CHECK_FREE_SPACE_DISC, description, args);
                    ResultTask resultTask = ResearchTest.executerClasse(executeTask);
                    log.info(resultTask.toString());
                } else {
                    log.error("TestType non pris en charge : " + testType);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
