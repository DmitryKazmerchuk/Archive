import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void zipFiles(String zip_out, List<String> zip_in) {
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
    public void deleteFiles(File file) {
        try {
            Files.delete(Path.of(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
