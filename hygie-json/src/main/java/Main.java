import java.io.File;

import static com.hygie.research.ReadFile.read;

public class Main {

    public static void main(String[] args) {
        File file = new File("fiche01.json");
        read(file);
    }
}
