package sample;

import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Created by rasmusjansson on 19/10/15.
 */
public class GameBoardController {

    @FXML
    private GridPane gameBoard;
    @FXML
    private Label testLabel;

    private HBox hBox;
    private int squareSelectedColumn;
    private int squareSelectedRow;
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
                                squareSelectedRow=0;
                            }else {
                                squareSelectedRow=GridPane.getRowIndex(node);
                            }
                            if (GridPane.getColumnIndex(node)==null){
                                squareSelectedColumn=0;
                            }else {
                                squareSelectedColumn=GridPane.getColumnIndex(node);
                            }
                            ui.setTestLabel((squareSelectedRow + 1) + "/" + (squareSelectedColumn + 1));
                            if (!ui.placeOccupied(squareSelectedRow,squareSelectedColumn)){
                                try {
                                    ((HBox) node).getChildren().add((Node) FXMLLoader.load(getClass().getResource("disklayoutblack.fxml")));
                                    ui.placeDisk(squareSelectedRow, squareSelectedColumn);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
