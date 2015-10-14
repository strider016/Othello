/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import game.Game;
/**
 *
 * @author Zetterman
 */

public class UI extends Application {
    
    private Game game;
    
    public static void main(String[] args)  { 
        launch(args);
        
    }
    
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Othello");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        
        
        
        
        
    }
}
