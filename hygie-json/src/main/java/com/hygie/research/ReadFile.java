package com.hygie.research;

import java.io.File;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

                // Récupérer les valeurs de testType et description
                String testType = (String) jsonObject.get("testType");
                String description = (String) jsonObject.get("description");

                // Afficher les valeurs
                System.out.println("Test Type: " + testType);
                System.out.println("Description: " + description);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
