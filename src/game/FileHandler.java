package game;

import java.io.*;
import javafx.collections.ObservableList;

/**
 * Created by rasmusjansson on 19/10/15.
 */

@SuppressWarnings("unchecked")
public class FileHandler {
 
    public ObservableList<Player> read() throws Exception {

        ObjectInputStream ois = null;
        ObservableList<Player> highscoreList;
        try {
            FileInputStream fin = new FileInputStream("highscore.ser");
            ois = new ObjectInputStream(fin);

            highscoreList = (ObservableList<Player>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file, creating new...");
            return null;
        } finally {
            if (ois != null) {
                ois.close();
            }

        }
        return highscoreList;
    }
    
    public void write(ObservableList<Player> books) throws Exception {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fout = new FileOutputStream("highscore.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(books);

        } finally {
            if (oos != null) {
                oos.close();
            }
        }
    }
}
