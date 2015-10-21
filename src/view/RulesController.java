/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Zetter
 */
public class RulesController {
    @FXML
    private TextFlow rules;

    
    private UI ui;

    @FXML
    private void initialize(){
    }

    public void setUi(UI ui){
        this.ui=ui;
    }
}
