package sample;

//import javafx.event.EventHandler;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.Node;

import javax.management.monitor.MonitorSettingException;
import java.awt.event.MouseEvent;

/**
 * Created by rasmusjansson on 19/10/15.
 */
public class GameBoardController {

    @FXML
    private GridPane gameBoard;
    @FXML
    private Label testLabel;

    private UI ui;

    @FXML
    private void initialize(){

    }

    public void setUI(UI ui){
        this.ui=ui;
    }

    @FXML
    private void handleClickOnSquare(){
        testLabel.setText(" You pressed \n a square \n");
        //ui.setPlayerOneScore("You pressed \nsquare: \n" + gameBoard.getColumnConstraints());

    }

}
