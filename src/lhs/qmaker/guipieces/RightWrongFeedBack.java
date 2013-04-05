package lhs.qmaker.guipieces;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author jordan
 */
public class RightWrongFeedBack extends AnchorPane {
    
    @FXML private TextArea correctTextArea;
    @FXML private TextArea incorrectTextArea;
    
    public RightWrongFeedBack() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RightWrongFeedBack.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    public ArrayList<String> getFeedback() {
        ArrayList<String> feedback = new ArrayList<String>();
        feedback.add(correctTextArea.getText());
        feedback.add(incorrectTextArea.getText());
        return feedback;
    }

    public void setFeedback(ArrayList<String> choices, ArrayList<String> feedback) {
        correctTextArea.setText(feedback.get(0));
        incorrectTextArea.setText(feedback.get(1));
    }
    
}
