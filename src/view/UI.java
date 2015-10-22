/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.util.ArrayList;

import game.Player;
import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import game.Game;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
/**
 *
 * @author Zetterman
 */

public class UI extends Application {
    
    private Game game;
    private BorderPane rootPane;
    private Stage primaryStage;
    private Stage highscoreStage;
    private Stage rulesStage;
    private Controller testcontroller;
    private GameBoardController gameBoardController;
    private boolean turn;
    private AnimationTimer timer;
    private ArrayList<String> availableSlots;


    public UI() {
        game = new Game();
        game.addHighscoreTest();
        game.sortHighScore();
    }

    public static void main(String[] args)  {
        launch(args);
    }

    protected class BlinkTimer extends AnimationTimer{
        private long previousNS = 0;
        private int row,column;


        @Override
        public void handle(long nowNs) {
            if (previousNS == 0){
                previousNS = nowNs;
            }

            previousNS=nowNs;

            for (int i = 0;i<availableSlots.size();i++){
                String tmp = availableSlots.get(i);
                String[] tmparr = tmp.split("[^\\d]+");
                row = Integer.parseInt(tmparr[0]);
                column = Integer.parseInt(tmparr[1]);
                gameBoardController.blinkAvailableSlot(row,column);
            }

            if (game.isGameOver()){
                timer.stop();
            }

        }
    }

    public void initBackgroundLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UI.class.getResource("rootwindow.fxml"));
            rootPane = loader.load();
            Scene scene = new Scene(rootPane);
            primaryStage.setScene(scene);
            primaryStage.show();

            //Controller controller = loader.getController();
            testcontroller = loader.getController();
            testcontroller.setUI(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void initGameBoard(){
        try{
            //800 width, 542 height
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UI.class.getResource("gameboardlayout.fxml"));
            GridPane page = loader.load();

            rootPane.setCenter(page);

            //GameBoardController controller = loader.getController();
            gameBoardController = loader.getController();
            gameBoardController.setUI(this);
            gameBoardController.setPane(page);
            gameBoardController.newBoard();
            gameBoardController.handleSquareSelected();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void initHighscoreWindow(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UI.class.getResource("highscore.fxml"));
            BorderPane page = loader.load();

            rulesStage = new Stage();
            rulesStage.setTitle("Highscore");
            rulesStage.initModality(Modality.WINDOW_MODAL);
            rulesStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            rulesStage.setScene(scene);


            HighscoreController controller = loader.getController();
            //controller.setHighscoreTable(this);

            rulesStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void initRulesWindow(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UI.class.getResource("rulesLayout.fxml"));
            TextFlow text = loader.load();
            
            Text rule1 = new Text("Rule 1: White begins by placing a piece, white-side up, adjacent to a black piece and opposite another white piece, so that a line of one or more black pieces directly intervenes (horizontally, vertically or diagonally) between the two white pieces.");
            Text rule2 = new Text("\n\nRule 2: When White places a piece so that an adjacent black piece is between two whites, the black piece is surrendered, or flipped over to white.");
            Text rule3 = new Text("\n\nRule 3: You may capture one or more pieces on a given turn. Also, you may capture any number of your opponent's pieces in one or more rows diagonally, vertically and horizontally.");
            Text winCondition = new Text("\n\nWin condition: The winner is the player with the most pieces of his or her color on the board at the end of the game. You may also win by completely eradicating your opponent's color from the game board.");
            
            text.getChildren().addAll(rule1, rule2, rule3, winCondition);
            
            highscoreStage = new Stage();
            highscoreStage.setTitle("Rules");
            highscoreStage.initModality(Modality.WINDOW_MODAL);
            highscoreStage.initOwner(primaryStage);
            Scene scene = new Scene(text);
            highscoreStage.setScene(scene);


            RulesController controller = loader.getController();
            

            //controller.setHighscoreTable(this);

            highscoreStage.showAndWait();
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Othello");

        timer = new BlinkTimer();
        timer.start();

        initBackgroundLayout();
        initGameBoard();
        newGame();
        availableSlots = game.getAvailableSlots(turn);
    }

    public void newGame(){
        game.newGame();
        initGameBoard();
        turn=false;
    }

    public void writeToFile(){
        game.writeToFile();
    }
    
    public String getPlayerOneName(){
        //return game.getPlayerOneName();
        return "Jeffrey";
    }

    public String getPlayerOneScore(){
        //return game.getPlayerOneScore();
        return "0";
    }

    public String getPlayerTwoName(){
        //return game.getPlayerTwoScore();
        return "Milley";
    }

    public String getPlayerTwoScore(){
        //return game.getPlayerTwoScore();
        return "0";
    }

    public void setTestLabel(String text){
        if (turn) {
            testcontroller.setTestLabel(text + "    Black");
        }else {
            testcontroller.setTestLabel(text + "    White");
        }
    }

    public ObservableList<Player> getHighscore(){
        return game.getHighscore();
    }

    public void placeDisk(int row, int column){
        //printAvailbleSlots();
        game.placeDisk(row,column,turn);
        changeTurn();
        printAvailbleSlots();
        availableSlots = game.getAvailableSlots(turn);

    }

    public boolean placeOccupied(int row, int column){
        return game.placeOccupied(row,column);
    }

    /**
     * Get which player turn it is.<br>
     * False if player 1 turn.
     * True if player 2 turn
     * @return boolean
     */
    public boolean getPlayerTurn(){
        return turn;
    }

    private void changeTurn(){
        turn ^= true;
    }

    public ArrayList<String> getAvailableSlots(){
        return game.getAvailableSlots(turn);
    }

    public ArrayList<String> getLocationDisks(){
        return game.getLocationDisks(turn);
    }

    public void printBoard(){
        game.printPlacedBoard();
    }

    public void printAvailbleSlots(){
        int row,column;
        ArrayList<String> b = getAvailableSlots();
        System.out.println("------------");
        for (int i = 0;i<b.size();i++){
            String tmp = b.get(i);
            String[] tmparr = tmp.split("[^\\d]+");
            row = Integer.parseInt(tmparr[0]);
            column = Integer.parseInt(tmparr[1]);
            System.out.println(row + "/" + column);
        }
    }
}
