package game;

import java.io.*;
import javafx.collections.ObservableList;

/**
 * A filehandler. Contains a read and write methods. It read and writes to a file named "highscore.ser"
 * @author Joachim Zetterman & Rasmus Jansson.
 */

@SuppressWarnings("unchecked")
public class FileHandler {
    
    /**
     * Reads the file "highscore.ser" and filles a list with read players.
     * Returns a reference to this list. 
     * @return highscoreList, of type ObservableList<Player>
     * @throws Exception 
     */
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
    
    /**
     * Writes to the file "highscore.ser". Inputed variable should be of type ObservableList<Player> 
     * @param highscoreList, of type ObservableList<Player> 
     * @throws Exception 
     */
    public void write(ObservableList<Player> highscoreList) throws Exception {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fout = new FileOutputStream("highscore.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(highscoreList);

        } finally {
            if (oos != null) {
                oos.close();
            }
        }
    }
}
