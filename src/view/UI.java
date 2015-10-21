/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;

import game.Player;
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
    private boolean turn;

    public UI() {
        game = new Game();
        game.addHighscoreTest();
        game.sortHighScore();
    }

    public static void main(String[] args)  {
        launch(args);
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

            GameBoardController controller = loader.getController();
            controller.setUI(this);
            controller.setPane(page);
            controller.newBoard();
            controller.handleSquareSelected();
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
        

        initBackgroundLayout();
        initGameBoard();
    }

    public void newGame(){
        game.newGame();
        initGameBoard();
    }

    public Game getGame(){
        return this.game;
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
        testcontroller.setTestLabel(text);
    }

    public ObservableList<Player> getHighscore(){
        return game.getHighscore();
    }

    public void placeDisk(int row, int column){
        game.placeDisk(row,column);
    }

    public boolean placeOccupied(int row, int column){
        if (game.placeOccupied(row,column)){
            return true;
        }else {
            return false;
        }
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

    public void changeTurn(){
        turn ^= true;
    }

    public void printBoard(){
        game.printPlacedBoard();
    }
}
