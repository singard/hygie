package com.hygie.hygiejson.filemanager;

import static com.hygie.hygiejson.filemanager.ReadFile.createJsonFile;
import static com.hygie.hygiejson.filemanager.ReadFile.read;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.hygie.hygietask.model.ExecuteTask;
import com.hygie.hygietask.model.ResultTask;
import com.hygie.hygietask.model.TaskType;
import com.hygie.hygietask.research.ResearchTest;

public class Main {
    public static void main(String[] args) {
    	String DEFAULT_PLAYBOOK_PATH = System.getProperty("user.dir");

		DEFAULT_PLAYBOOK_PATH = DEFAULT_PLAYBOOK_PATH.concat(File.separator)
				.concat("data")
				.concat(File.separator)
				.concat("fiche01").concat(".json");
		
        File file = new File(DEFAULT_PLAYBOOK_PATH);
        
        List<ResultTask> resultTasks = read(file);
        for (ResultTask task : resultTasks) {
            
            System.out.println(task);
    		
        }

        // Créer une liste immuable de tâches
        List<ExecuteTask> taskList = Arrays.asList(
                new ExecuteTask(TaskType.CHECK_FREE_SPACE_DISC, "Description 1", new String[]{"arg1", "arg2"}),
                new ExecuteTask(TaskType.CHECK_TOTAL_RAM, "Description 2", new String[]{"arg3", "arg4", "arg5"})
        );

        // Appeler la fonction pour créer le fichier JSON
        createJsonFile("output.json", taskList);
    }
}
