import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress jade = new GameProgress(100, 100, 10, 1_000);
        GameProgress kira = new GameProgress(90, 90, 9, 900);
        GameProgress kitana = new GameProgress(80, 80, 8, 800);

        saveGame(jade, "c://Games/savegames/save_jade.dat");
        saveGame(kira, "c://Games/savegames/save_kira.dat");
        saveGame(kitana, "c://Games/savegames/save_kitana.dat");

        zipFiles("c://Games/savegames/save.zip", list);
    }

    static List<String> list = new ArrayList<>();

    public static void saveGame(GameProgress gameProgress, String path) {
        list.add(path);
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zip_out, List<String> zip_in) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zip_out));) {
            for (int i = 0; i < zip_in.size(); i++) {
                File file = new File(zip_in.get(i));
                FileInputStream fis = new FileInputStream(zip_in.get(i));
                ZipEntry entry = new ZipEntry(file.getName());
                zout.putNextEntry(entry);

                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
                deleteFiles(file);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteFiles(File file) {
        try {
            Files.delete(Path.of(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
