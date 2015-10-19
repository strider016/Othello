package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import game.Player;

/**
 * Created by rasmusjansson on 17/10/15.
 */
public class HighscoreController {
    @FXML
    private TableView<Player> highscoreTable;
    @FXML
    private TableColumn <Player,String> usernameColumn;
    @FXML
    private TableColumn<Player,String> scoreColumn;

    private UI ui;

    @FXML
    private void initialize(){
        usernameColumn.prefWidthProperty().bind(highscoreTable.widthProperty().multiply(0.75));
        scoreColumn.prefWidthProperty().bind(highscoreTable.widthProperty().multiply(0.25));
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().endScoreProperty());
    }

    public void setHighscoreTable(UI ui){
        this.ui=ui;
        highscoreTable.setItems(ui.getHighscore());
    }
}
