package sample;

import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * Created by rasmusjansson on 19/10/15.
 */
public class GameBoardController {

    @FXML
    private GridPane gameBoard;
    @FXML
    private Label testLabel;

    private int squareSelectedX;
    private int squareSelectedY;
    private UI ui;
    private GridPane gridPane;

    @FXML
    private void initialize(){

    }

    public void setUI(UI ui){
        this.ui=ui;
    }

    public void handleSquareSelected(GridPane gridPane){
        this.gridPane=gridPane;

        gridPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for (Node node:gridPane.getChildren()){
                    if (node instanceof HBox){
                        if (node.getBoundsInParent().contains(e.getSceneX(),e.getSceneY())){
                            if (GridPane.getRowIndex(node)==null){
                                squareSelectedY=0;
                            }else {
                                squareSelectedY=GridPane.getRowIndex(node);
                            }
                            if (GridPane.getColumnIndex(node)==null){
                                squareSelectedX=0;
                            }else {
                                squareSelectedX=GridPane.getColumnIndex(node);
                            }
                            ui.setTestLabel((squareSelectedY+1) + "/" + (squareSelectedX+1));
                            ui.placeDisk(squareSelectedY,squareSelectedX);
                        }
                    }
                }
            }
        });
    }
}
