package game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Highscore class. Contains a list of high score of type ObservableList<Player>
 * @author Joachim Zetterman & Rasmus Jansson.
 */
public class HighScore{
   
    private ObservableList<Player> highscoreList = FXCollections.observableArrayList();
    
    /**
     * Default constructor, does not call any method
     */
    public HighScore(){

    }
    
    /**
     * Gets the high score list of type ObservableList<Player>
     * @return highscoreList
     */
    public ObservableList<Player> getHighscoreList(){
        return highscoreList;
    }
    
    /**
     * Adds a new player p to the highscore list.
     * @param p 
     */
    public void addNewHighscore(Player p){
        highscoreList.add(p);
    }
    
    public String playerAt(int pos){
        return "empty";
    }
}
