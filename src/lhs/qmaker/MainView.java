/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lhs.qmaker;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import lhs.qmaker.guipieces.BaseCreation;
import lhs.qmaker.matching.MatchingCreation;
import lhs.qmaker.multiplechoice.MultiChoiceCreation;
import lhs.qmaker.select.SelectCreation;

/**
 * FXML Controller class
 *
 * @author jordan
 */
public class MainView implements Initializable {
    public static MainView mainView;
    @FXML
    private MenuBar menuBar;
    @FXML private AnchorPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mainView = this;
    }    

    @FXML
    private void newMultipleChoice(ActionEvent event) {
        setContent(new MultiChoiceCreation());
    }

    @FXML
    private void newMatching(ActionEvent event) {
        setContent(new MatchingCreation());
    }

    @FXML
    private void newSelectAll(ActionEvent event) {
        setContent(new SelectCreation());
    }
    
    public void setContent(Node node) {
        content.getChildren().clear();
        content.getChildren().add(node);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
    }
    
    @FXML
    public void cloneAndEdit(ActionEvent event) {
        setContent(new SearchExistingQuestions(true));
    }
    
    @FXML
    public void displayGuide(ActionEvent event) {
        setContent(new Guide());
    }
    
    @FXML
    public void displayAbout(ActionEvent event) {
        setContent(new About());
    }
}
