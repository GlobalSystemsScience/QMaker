/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lhs.qmaker;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Jordan
 */
public class About extends AnchorPane {
    public About() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(QuestionCompleteDialog.class.getResource("About.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }
}
