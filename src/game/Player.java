package game;

import java.util.Comparator;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The player class for the game Othello. A player will be able to controll all the actions on the board. AI will also have the same Player rights but it's algorithms will in return only do specific things.
 * @author Joachim Zetterman & Rasmus Jansson.
 */
public class Player {

    private int posX;
    private int posY;
    private int currentScore;
    private SimpleStringProperty username;
    private SimpleStringProperty endScore;
    
    private  Disk.Color color;

    /**
     * Default constructor
     */
    public Player(){
        //this(null,null);
    }
    
    /**
     * Takes in a username, endScore and the type of color of the disk.
     * @param username, String
     * @param endScore, String
     * @param color, of the type Disk.Color
     */
    public Player(String username,String endScore, Disk.Color color){
        this.username=new SimpleStringProperty(username);
        this.endScore=new SimpleStringProperty(endScore);
        posX = 0;
        posY = 0;
        currentScore = 0;
        
        this.color = color;
    }
    
    /**
     * Compares the player endscore points. Returns -1 if the p1 has a greater value then p2. Returns 1 if the p1 has a less value then p2. Otherwise it returns 0 if they are equal
     * @return -1, 1 or 0
     */
    public static Comparator<Player> getCompByEndScore()
        {   
         Comparator comp = new Comparator<Player>(){
             @Override
             public int compare(Player p1, Player p2)
             {   
                 if(p1.getEndScoreInInt() > p2.getEndScoreInInt()){
                     return -1;
                 }
                 else if(p1.getEndScoreInInt() < p2.getEndScoreInInt()){
                     return 1;
                 }
                 else{
                     return 0;
                 }
             }        
         };
         return comp;
    }  
    
    /**
     * Gets the pos X of the player
     * @return posX, int
     */
    public int getPosX(){
        return posX;
    }
    
    /**
     * Gets the pos Y of the player
     * @return posY, int
     */
    public int getPosY() {
        return posY;
    }
    
    /**
     * Gets the current score of the player
     * @return currentScore, int
     */
    public int getCurrentScore() {
        return currentScore;
    }
    
    /**
     * Gets the username of the player
     * @return 
     */
    public String getUsername() {
        return username.get();
    }
    
    /**
     * Sets the username of the player
     * @param username , string
     */
    public void setUsername(String username){
        this.username.set(username);
    }
    
    /**
     * Gets the username property of the current player
     * @return 
     */
    public StringProperty usernameProperty() {
        return username;
    }
    
    /**
     * Gets the end score of the player
     * @return 
     */
    public String getEndScore() {
        return endScore.get();
    }
    
    /**
     * Sets the end score of the player
     * @param endScore 
     */
    public void setEndScore(String endScore){
        this.endScore.set(endScore);
    }
    
    /**
     * Gets the end score property of the player
     * @return endScore, StringProperty
     */
    public StringProperty endScoreProperty() {
        return endScore;
    }
    
    /**
     * Gets the endscore property in type int (transforms from StringProperty to Int)
     * @return 
     */
    public int getEndScoreInInt() {
        return Integer.parseInt(getEndScore());
    }
    
    /**
     * Gets the current score in transformed type String
     * @return 
     */
    public String getCurrentScoreString(){
        return Integer.toString(currentScore);
    }

    public Disk.Color getColor(){
        return color;
    }

    public void placeDisk(){

    }
}
