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
import javafx.scene.layout.AnchorPane;
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
    private Stage newGameStage;
    private Controller testcontroller;
    private GameBoardController gameBoardController;

    private boolean turn;
    private AnimationTimer timer;
    private ArrayList<String> availableSlots;
    private boolean gameRunning;


    public UI() {
        try {
            game = new Game();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

            if (gameRunning) {
                for (int i = 0; i < availableSlots.size(); i++) {
                    String tmp = availableSlots.get(i);
                    String[] tmparr = tmp.split("[^\\d]+");
                    row = Integer.parseInt(tmparr[0]);
                    column = Integer.parseInt(tmparr[1]);
                    gameBoardController.blinkAvailableSlot(row, column,availableSlots.size());
                }
            }
            
            
            if (game.isGameOver(turn)){
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

            Stage highscoreStage = new Stage();
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
    
    public void initRulesWindow(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UI.class.getResource("rulesLayout.fxml"));
            AnchorPane page = loader.load();

            Stage rulesStage = new Stage();
            rulesStage.setTitle("Rules");
            rulesStage.initModality(Modality.WINDOW_MODAL);
            rulesStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            rulesStage.setScene(scene);

            RulesController controller = loader.getController();
            controller.placeText();

            rulesStage.showAndWait();
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void initNewGameWindow(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UI.class.getResource("newgamelayout.fxml"));
            AnchorPane page = loader.load();

            //Stage newGameStage = new Stage();
            newGameStage = new Stage();
            newGameStage.setTitle("New Game");
            newGameStage.initModality(Modality.WINDOW_MODAL);
            newGameStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            newGameStage.setScene(scene);

            NewGameController controller = loader.getController();
            controller = loader.getController();
            controller.setUi(this);

            newGameStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Othello");

        timer = new BlinkTimer();


        initBackgroundLayout();
    }

    public void newGame(String usrPlayerOne, String usrPlayerTwo, boolean mode){
        newGameStage.close();
        game.newGame(usrPlayerOne,usrPlayerTwo,mode);
        testcontroller.showGameInformation();
        initGameBoard();
        turn=false;
        gameRunning = true;
        availableSlots = game.getAvailableSlots(turn);
        timer.start();
    }

    public void writeToFile(){
        try {
            game.writeToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getPlayerOneName(){
        return game.getPlayerOneName();
    }

    public String getPlayerOneScore(){
        return game.getPlayerOneScore();
    }

    public String getPlayerTwoName(){
        return game.getPlayerTwoName();
    }

    public String getPlayerTwoScore(){
        return game.getPlayerTwoScore();
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
        game.placeDisk(row,column,turn);
        testcontroller.showGameInformation();

        //changeColorOnDisk(row, column);
        changeColorOfDisks(row,column);
        //printAvailbleSlots();
        //changeTurn();
        availableSlots = game.getAvailableSlots(turn);

    }
    public void changeColorOfDisks(int rowP,int colP){
        ArrayList<String> tmpArr = game.getDisksToBeChanged(rowP,colP,turn);
        int row,column;
        System.out.println("-------------");
        for (int i = 0;i<tmpArr.size();i++){
            String tmp = tmpArr.get(i);
            String[] tmparr = tmp.split("[^\\d]+");
            row = Integer.parseInt(tmparr[0]);
            column = Integer.parseInt(tmparr[1]);
            changeColorOnDisk(row,column);
            game.changeColorOfDisk(row,column,turn);
            System.out.println(row + "/" + column);
        }
    }

    public boolean placeOccupied(int row, int column){
        return game.placeOccupied(row,column);
    }

    public void changeColorOnDisk(int row, int column){
        gameBoardController.changeColorOnDisk(row,column,turn);
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
