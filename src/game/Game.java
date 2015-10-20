/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 *
 * @author Zetterman
 */


public class Game {
    private Board board;
    private Rules rules;
    private HighScore highScore;
    private FileHandler fileHandler;

    public Game(){
        board = new Board(); //sets 4 center disks?
        rules = new Rules();
        highScore = new HighScore();
        game();
    }
    
    public void newGame(){
        board = new Board();
        game();
    }
    
    public void game(){
        
        //Some sort of while loop
        
        
    }
    
    public void addHighscoreTest(){
        highScore.addNewHighscore(new Player("Kalle","25"));
        highScore.addNewHighscore(new Player("Janne","35"));
        highScore.addNewHighscore(new Player("Anton","27"));
        highScore.addNewHighscore(new Player("Joachim","38"));
        highScore.addNewHighscore(new Player("Rasmus", "39"));
        highScore.addNewHighscore(new Player("Joachim", "42"));
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
}
