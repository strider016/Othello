/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Game class for the game Othello.
 * This class contains and controlls all the other classes in the package. This class can be connected to a UI class in order to alter data.
 * @author Joachim Zetterman & Rasmus Jansson.
 */
public class Game implements Serializable{
    private Board board;
    private HighScore highScore;
    private FileHandler fileHandler = new FileHandler();
    private Player playerOne;
    private Player playerTwo;
    
    /**
     * Constructor for Game.
     * Creates a new board and highscore. It also reads in all the eventual highscore data and adds it to the highscore list.
     */
    public Game() {
        board = new Board(); //sets 4 center disks?
        highScore = new HighScore();
        
        try {
            highScore.getHighscoreList().addAll(fileHandler.read());
        } catch (Exception ex) {
            
        }
        
//        for(Player p : highScore.getHighscoreList()){
//            System.out.println("Players : " + p.getEndScore());
//        }

    }
    
    public void newGame(String usrPlayerOne,String usrPlayerTwo,boolean mode){
        board = new Board();
        if (mode){
            playerOne = new Player(usrPlayerOne,"0", Disk.Color.WHITE);
            playerTwo = new Player(usrPlayerTwo,"0", Disk.Color.BLACK);
        }else {
            playerOne = new Player(usrPlayerOne,"0",Disk.Color.WHITE);
            //playerTwo = new AI(usrPlayerTwo,"0",Disk.Color.BLACK);
        }

    }
    
    /**
     * Sorts the high score list with a custome made comparator. The list is sorted with the highest scored players at the top to bottom.
     */
    public void sortHighScore(){
        Collections.sort(highScore.getHighscoreList(), Player.getCompByEndScore());
    }
    
    public void addHighscoreTest(){
        highScore.addNewHighscore(new Player("Kalle","39", Disk.Color.BLACK));
        highScore.addNewHighscore(new Player("Janne","35", Disk.Color.BLACK));
        highScore.addNewHighscore(new Player("Anton","1", Disk.Color.BLACK));
        highScore.addNewHighscore(new Player("Joachim","38", Disk.Color.BLACK));
        highScore.addNewHighscore(new Player("Rasmus", "3", Disk.Color.BLACK));
        highScore.addNewHighscore(new Player("Joachim", "42", Disk.Color.BLACK));
        
    }
    
    /**
     * Add a players highscore to the high score list
     * @param p 
     */
    public void addHighScore(Player p){
        highScore.addNewHighscore(p);
    }
    
    /**
     * Gets the highscore list of the type ObservableList<Player>
     * @return 
     */
    public ObservableList<Player> getHighscore(){
        return highScore.getHighscoreList();
    }

    /**
     *  Places a disk on desired position. If the place is already occupied it will not place.
     *
     * @param row int row on board
     * @param column int column on board
     * @param player boolean of whos turn it is, false = player 1, true = player 2
     */
    public void placeDisk(int row, int column,boolean player){
        if (!board.placedOccupied(row, column)){
            board.placeDisk(row,column,player);
            playerOne.setCurrentScore(board.getLocationDisks(playerOne).size());
            playerTwo.setCurrentScore(board.getLocationDisks(playerTwo).size());
        }
    }
    
    /**
     * Checks if the place is occupied via the inputed position. Returns true if the place is occupied. 
     * @param row
     * @param column
     * @return true or false
     */
    public boolean placeOccupied(int row, int column){
        return board.placedOccupied(row, column);
    }

    public void printPlacedBoard(){
        ArrayList<ArrayList<Disk>> b = board.getBoard();
        System.out.println("   1 2 3 4 5 6 7 8");
        System.out.println("  ----------------");
        for (int i = 0;i<8;i++){
            System.out.print(i+1 + "|");
            for (int j = 0;j<8;j++){
                if (b.get(i).get(j).isPlaced()==true){
                    System.out.print(" 1");
                }else {
                    System.out.print(" 0");
                }
            }
            System.out.print("\n");
        }
    }
    
    /**
     * Write the highscore list to the file specified in the filehandler class
     */
    public void writeToFile(){ 
        try {
            fileHandler.write(highScore.getHighscoreList());
        } catch (Exception ex) {
            System.out.println("Could no write the highscorelist to file");
        }
    }
    
    /**
     * Gets the player one name
     * @return ,a reference of the type String
     */
    public String getPlayerOneName(){
        return playerOne.getUsername();
    }
    
    /**
     * Gets the player one score
     * @return ,a reference of the type String
     */
    public String getPlayerOneScore(){
        return playerOne.getCurrentScoreString();
    }
    
     /**
     * Gets the player two name
     * @return ,a reference of the type String
     */
    public String getPlayerTwoName(){
        return playerTwo.getUsername();
    }
    
     /**
     * Gets the player two score
     * @return ,a reference of the type String
     */
    public String getPlayerTwoScore(){
        return playerTwo.getCurrentScoreString();
    }
    
    public ArrayList<String> getAvailableSlots(boolean turn){
        if (turn){
            return board.getAvailableSlots(playerTwo);
        }else {
            return board.getAvailableSlots(playerOne);
        }
    }

    public ArrayList<String> getLocationDisks(boolean turn){
        if (turn){
            return board.getLocationDisks(playerTwo);
        }else {
            return board.getLocationDisks(playerOne);
        }
    }

    public boolean isGameOver(){
        return false;
    }
}
