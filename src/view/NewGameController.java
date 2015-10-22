package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


/**
 * Created by Razmus on 2015-10-22.
 */
public class NewGameController {
    @FXML
    private TextField playerOne;
    @FXML
    private TextField playerTwo;
    @FXML
    private Button newGameButton;
    @FXML
    private RadioButton onePlayerMode;
    @FXML
    private RadioButton twoPlayerMode;

    private UI ui;
    private final String bot = "Computer";
    private final ToggleGroup group = new ToggleGroup();

    @FXML
    private void initialize(){
        playerOne.setPromptText("Player 1 username");
        playerTwo.setPromptText("Player 2 username");
        onePlayerMode.setToggleGroup(group);
        onePlayerMode.setSelected(true);
        twoPlayerMode.setToggleGroup(group);

    }

    public void setUi(UI ui){
        this.ui=ui;
    }
    @FXML
    private void handleNewGameButton(){
        if (group.getSelectedToggle()==onePlayerMode) {
            ui.newGame(playerOne.getText(),bot,false);
        }else if (group.getSelectedToggle()==twoPlayerMode){
            ui.newGame(playerOne.getText(),playerTwo.getText(),true);
            Stage stage = (Stage) newGameButton.getScene().getWindow();
            stage.close();
        }
    }
}
