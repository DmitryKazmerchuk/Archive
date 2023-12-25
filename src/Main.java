import java.io.*;

public class Main {
    public static void main(String[] args) {
        GameProgress jade = new GameProgress(100, 100, 10, 1_000);
        GameProgress kira = new GameProgress(90, 90, 9, 900);
        GameProgress kitana = new GameProgress(80, 80, 8, 800);
        Save saveGame = new Save();
        Zip zip = new Zip();

        saveGame.saveGame(jade, "c://Games/save_jade.dat");
        saveGame.saveGame(kira, "c://Games/save_kira.dat");
        saveGame.saveGame(kitana, "c://Games/save_kitana.dat");
        zip.zipFiles("c://Games/save.zip", saveGame.list);
    }
}
