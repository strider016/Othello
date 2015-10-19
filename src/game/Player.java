package game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by rasmusjansson on 18/10/15.
 */
public class Player {

    private int posX;
    private int posY;
    private int currentScore;
    private SimpleStringProperty username;
    private SimpleStringProperty endScore;

    public Player(){
        this(null,null);
    }

    public Player(String username,String endScore){
        this.username=new SimpleStringProperty(username);
        this.endScore=new SimpleStringProperty(endScore);
        posX = 0;
        posY = 0;
        currentScore = 0;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username){
        this.username.set(username);
    }
    public StringProperty usernameProperty() {
        return username;
    }

    public String getEndScore() {
        return endScore.get();
    }

    public void setEndScore(String endScore){
        this.endScore.set(endScore);
    }
    public StringProperty endScoreProperty() {
        return endScore;
    }
}
