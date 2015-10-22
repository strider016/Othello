/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Zetter
 */
public class RulesController {
    @FXML
    private TextFlow rulesTable;

    private Text rule1;
    private Text rule2;
    private Text rule3;
    private Text winCondition;
    
    private UI ui;

    @FXML
    private void initialize(){
        rule1 = new Text("Rule 1: White begins by placing a piece, white-side up, adjacent to a black piece and opposite another white piece, so that a line of one or more black pieces directly intervenes (horizontally, vertically or diagonally) between the two white pieces.");
        rule2 = new Text("\n\nRule 2: When White places a piece so that an adjacent black piece is between two whites, the black piece is surrendered, or flipped over to white.");
        rule3 = new Text("\n\nRule 3: You may capture one or more pieces on a given turn. Also, you may capture any number of your opponent's pieces in one or more rows diagonally, vertically and horizontally.");
        winCondition = new Text("\n\nWin condition: The winner is the player with the most pieces of his or her color on the board at the end of the game. You may also win by completely eradicating your opponent's color from the game board.");
    }

    public void placeText(){
        rulesTable.getChildren().addAll(rule1,rule2,rule3,winCondition);
        
    }
}
