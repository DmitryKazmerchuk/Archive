import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" + "health=" + health + ", weapons=" + weapons + ", lvl=" + lvl + ", distance=" + distance + '}';
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