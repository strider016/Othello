package view;

import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;

/**
 * Created by rasmusjansson on 19/10/15.
 */
public class GameBoardController {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label testLabel;

    private HBox hBox;
    private int squareSelectedColumn;
    private int squareSelectedRow;
    private UI ui;
    //private GridPane gridPane;
    private String diskcolor;

    @FXML
    private void initialize(){
        diskcolor="disklayoutwhite.fxml";
    }



    public void setUI(UI ui){
        this.ui=ui;
    }

    public void setPane(GridPane gridpane){
        this.gridPane=gridpane;
    }

    /**
     * Sets the the four beginning disks
     */
    public void newBoard(){
        Circle nodeWhiteTop = new Circle();
        Circle nodeWhiteBottom = new Circle();
        Circle nodeBlackTop = new Circle();
        Circle nodeBlackBottom = new Circle();
        try {
            nodeWhiteTop = FXMLLoader.load(getClass().getResource("disklayoutwhite.fxml"));
            nodeWhiteBottom = FXMLLoader.load(getClass().getResource("disklayoutwhite.fxml"));
            nodeBlackTop = FXMLLoader.load(getClass().getResource("disklayoutblack.fxml"));
            nodeBlackBottom = FXMLLoader.load(getClass().getResource("disklayoutblack.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        GridPane.setHalignment(nodeWhiteTop, HPos.CENTER);
        GridPane.setValignment(nodeWhiteTop, VPos.CENTER);
        GridPane.setHalignment(nodeWhiteBottom, HPos.CENTER);
        GridPane.setValignment(nodeWhiteBottom, VPos.CENTER);
        GridPane.setHalignment(nodeBlackTop, HPos.CENTER);
        GridPane.setValignment(nodeBlackTop, VPos.CENTER);
        GridPane.setHalignment(nodeBlackBottom, HPos.CENTER);
        GridPane.setValignment(nodeBlackBottom, VPos.CENTER);

        gridPane.add(nodeWhiteTop,3,3);
        gridPane.add(nodeWhiteBottom,4,4);
        gridPane.add(nodeBlackTop,3,4);
        gridPane.add(nodeBlackBottom,4,3);


    }

    public void handleSquareSelected(){
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
                                if (ui.getPlayerTurn()){
                                    diskcolor="disklayoutblack.fxml";
                                }else{
                                    diskcolor="disklayoutwhite.fxml";
                                }

                                try {
                                    ((HBox) node).getChildren().add((Node) FXMLLoader.load(getClass().getResource(diskcolor)));
                                    ui.placeDisk(squareSelectedRow, squareSelectedColumn);
                                    ui.changeTurn();
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
