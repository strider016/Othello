/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import game.Game;
/**
 *
 * @author Zetterman
 */

public class UI extends Application {
    
    private Game game;
    private BorderPane rootPane;
    private Stage primaryStage;
    
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

            Controller controller = loader.getController();
            controller.setUI(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Othello");

        initBackgroundLayout();
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
}
