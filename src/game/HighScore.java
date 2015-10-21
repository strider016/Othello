package game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Created by rasmusjansson on 19/10/15.
 */
public class HighScore{
   
    private ObservableList<Player> highscoreList = FXCollections.observableArrayList();

    public HighScore(){

    }

    public ObservableList<Player> getHighscoreList(){
        return highscoreList;
    }
    
    public void addNewHighscore(Player p){
        highscoreList.add(p);
    }

    public String playerAt(int pos){
        return "empty";
    }
}
