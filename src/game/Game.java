/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javafx.collections.ObservableList;

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
        board = new Board();
        rules = new Rules();
        highScore = new HighScore();
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

    public boolean placeDisk(int row, int column){
        if (board.placeDisk(row,column)){
            return true;
        }else {
            return false;
        }
    }
}
