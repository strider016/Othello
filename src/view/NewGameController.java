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
        Stage stage = (Stage) newGameButton.getScene().getWindow();
        if (group.getSelectedToggle()==onePlayerMode) {
            if (playerOne.getText().isEmpty()){
                ui.newGame("Player 1",bot,false);
            }else {
                ui.newGame(playerOne.getText(), bot, false);
            }
            stage.close();
        }else if (group.getSelectedToggle()==twoPlayerMode){
            if (playerOne.getText().isEmpty() && playerTwo.getText().isEmpty()){
                ui.newGame("Player 1","Player 2",true);
            }else if (playerOne.getText().isEmpty()){
                ui.newGame("Player 1",playerTwo.getText(),true);
            }else {
                ui.newGame(playerOne.getText(), playerTwo.getText(), true);
            }

            stage.close();
        }
    }
}
