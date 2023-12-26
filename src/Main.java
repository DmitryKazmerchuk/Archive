import java.io.*;

public class Main {
    public static void main(String[] args) {
        GameProgress jade = new GameProgress(100, 100, 10, 1_000);
        GameProgress kira = new GameProgress(90, 90, 9, 900);
        GameProgress kitana = new GameProgress(80, 80, 8, 800);

        jade.saveGame(jade,"c://Games/savegames/save_jade.dat");
        kira.saveGame(kira, "c://Games/savegames/save_kira.dat");
        kitana.saveGame(kitana, "c://Games/savegames/save_kitana.dat");

        GameProgress.zipFiles("c://Games/savegames/save.zip", GameProgress.list);
    }
}
