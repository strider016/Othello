package view;

import game.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.*;

public class Controller {
    @FXML
    private TableView<Player> testView;
    @FXML
    private TableColumn<Player,String> testColumn;
    @FXML
    private Label playerOneLabel;
    @FXML
    private Label playerTwoLabel;
    @FXML
    private Label playerOneScoreLabel;
    @FXML
    private Label playerTwoScoreLabel;
    @FXML
    private Label notCompleted;
    @FXML
    private Label testLabel;

    private String TBA = "Not yet implemented: ";
    private UI ui;

    public Controller(){
    }

    @FXML
    private void initialize(){
        notCompleted.setText("");
    }

    public void setUI(UI ui){
        this.ui = ui;
    }

    public void setTestLabel(String text){
        notCompleted.setText(text);
    }

    private void showGameInformation(){
        playerOneLabel.setText(ui.getPlayerOneName());
        playerOneScoreLabel.setText(ui.getPlayerOneScore());
        playerTwoLabel.setText(ui.getPlayerTwoName());
        playerTwoScoreLabel.setText(ui.getPlayerTwoScore());
    }
    
    private void startGame(){
        ui.newGame();
    }

    @FXML
    private void HandleExitButton(){
        ui.writeToFile();
        System.exit(0);
    }

    @FXML
    private void HandleNewGameButton(){
        notCompleted.setText(TBA + "New Game");
        //testLabel.setText(TBA);
        showGameInformation();
        ui.printBoard();
        ui.newGame();
    }

    @FXML
    private void HandleHighscoreButton(){
        ui.initHighscoreWindow();
    }

    @FXML
    private void HandleRulesButton(){
        notCompleted.setText(TBA + "Rules");
    }
}