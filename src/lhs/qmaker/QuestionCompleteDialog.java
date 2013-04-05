package lhs.qmaker;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import lhs.qmaker.multiplechoice.FeedbackPane;


/**
 * FXML Controller class
 *
 * @author Jordan
 */
public class QuestionCompleteDialog extends AnchorPane {
    @FXML TextArea htmlBox;
    public QuestionCompleteDialog(String html) {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(QuestionCompleteDialog.class.getResource("QuestionCompleteDialog.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
            htmlBox.setText(html);
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }
}
