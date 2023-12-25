import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Save {
    List<String> list = new ArrayList<>();

    public void saveGame(GameProgress gameProgress, String path) {
        list.add(path);
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
