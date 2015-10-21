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
    private Player player1;
    private Player player2;

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
        game();
    }
    
    public void game(){
        
        //Some sort of while loop
        
        
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

    public void placeDisk(int row, int column){
        /*if (board.placeDisk(row,column)){
            return true;
        }else {
            return false;
        }*/
        if (!board.placedOccupied(row, column)){
            board.placeDisk(row,column);
        }
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
}
