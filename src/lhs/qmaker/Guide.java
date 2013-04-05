package lhs.qmaker;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Jordan
 */
public class Guide extends AnchorPane {
    public Guide() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(QuestionCompleteDialog.class.getResource("Guide.fxml"));
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
