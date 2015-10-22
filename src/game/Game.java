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
 *
 * @author Zetterman
 */

public class Game implements Serializable{
    private Board board;
    private Rules rules;
    private HighScore highScore;
    private FileHandler fileHandler = new FileHandler();
    private Player playerOne;
    private Player playerTwo;

    public Game() {
        board = new Board(); //sets 4 center disks?
        rules = new Rules();
        highScore = new HighScore();
        
        try {
            highScore.getHighscoreList().addAll(fileHandler.read());
        } catch (Exception ex) {
            
        }
        
        for(Player p : highScore.getHighscoreList()){
            System.out.println("Players : " + p.getEndScore());
        }

    }
    
    public void newGame(){
        board = new Board();
        playerOne = new Player("Rasmus","30", Disk.Color.WHITE);
        playerTwo = new Player("Joachim","30", Disk.Color.BLACK);
    }
    
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

    public void addHighScore(Player p){
        highScore.addNewHighscore(p);
    }

    public ObservableList<Player> getHighscore(){
        return highScore.getHighscoreList();
    }

    /**
     * Place a disk a specified location and who placed it
     *
     * @param row int row on board
     * @param column int column on board
     * @param player boolean of whos turn it is, false = player 1, true = player 2
     */
    public void placeDisk(int row, int column,boolean player){
        if (!board.placedOccupied(row, column)){
            board.placeDisk(row,column,player);
        }
    }

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
    
    public void writeToFile(){ 
        try {
            fileHandler.write(highScore.getHighscoreList());
        } catch (Exception ex) {
            System.out.println("Could no write the highscorelist to file");
        }
    }

    public String getPlayerOneName(){
        return playerOne.getUsername();
    }

    public String getPlayerOneScore(){
        return playerOne.getCurrentScoreString();
    }

    public String getPlayerTwoName(){
        return playerTwo.getUsername();
    }

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
