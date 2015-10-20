/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import java.util.Observable;

import game.Player;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import game.Game;
import javafx.scene.layout.GridPane;
/**
 *
 * @author Zetterman
 */

public class UI extends Application {
    
    private Game game;
    private BorderPane rootPane;
    private Stage primaryStage;
    private Stage highscoreStage;
    private Controller testcontroller;

    public UI(){
        game = new Game();
        game.addHighscoreTest();
    }

    public static void main(String[] args)  {
        launch(args);
    }
    
    public void initBackgroundLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UI.class.getResource("sample.fxml"));
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UI.class.getResource("gameboardlayout.fxml"));
            GridPane page = loader.load();

            rootPane.setCenter(page);

            GameBoardController controller = loader.getController();
            controller.setUI(this);
            controller.handleSquareSelected(page);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void initHighscoreWindow(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UI.class.getResource("highscore.fxml"));
            BorderPane page = loader.load();

            highscoreStage = new Stage();
            highscoreStage.setTitle("Highscore");
            highscoreStage.initModality(Modality.WINDOW_MODAL);
            highscoreStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            highscoreStage.setScene(scene);


            HighscoreController controller = loader.getController();
            controller.setHighscoreTable(this);

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

    public void printBoard(){
        game.printPlacedBoard();
    }
}
