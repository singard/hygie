import com.hygie.model.ExecuteTask;
import com.hygie.model.TaskType;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.hygie.research.ReadFile.createJsonFile;
import static com.hygie.research.ReadFile.read;

public class Main {
    public static void main(String[] args) {
        File file = new File("fiche01.json");
        read(file);

        // Créer une liste immuable de tâches
        List<ExecuteTask> taskList = Arrays.asList(
                new ExecuteTask(TaskType.CHECK_FREE_SPACE_DISC, "Description 1", new String[]{"arg1", "arg2"}),
                new ExecuteTask(TaskType.CHECK_TOTAL_RAM, "Description 2", new String[]{"arg3", "arg4", "arg5"})
        );

        // Appeler la fonction pour créer le fichier JSON
        createJsonFile("output.json", taskList);
    }
}