package lhs.qmaker.multiplechoice;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lhs.qmaker.guipieces.RightWrongFeedBack;

/**
 *
 * @author jordan
 */
public class FeedbackPane extends AnchorPane {
    
    @FXML AnchorPane inner;
    @FXML ToggleGroup type;
    @FXML RadioButton distinct;
    @FXML RadioButton correctIncorrect;
    private RightWrongFeedBack rightWrong = new RightWrongFeedBack();
    private ArrayList<String> existingChoices = new ArrayList<String>();
    private ArrayList<TextArea> multiComments = new ArrayList<TextArea>();
    
    public FeedbackPane() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(FeedbackPane.class.getResource("FeedbackPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
        EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                update(existingChoices);
            }
        };
        distinct.setOnAction(eh);
        correctIncorrect.setOnAction(eh);
    }
    
    public void update(ArrayList<String> choices) {
        if (choices.size() < 3) {
            correctIncorrect.selectedProperty().set(true);
            distinct.disableProperty().set(true);
        } else {
            distinct.disableProperty().set(false);
        }
        inner.getChildren().clear();
        AnchorPane comments;
        if (type.getSelectedToggle() == distinct) {
            comments = new MultiFeedBack(choices);
            
        } else {
            comments = rightWrong;
        }
        AnchorPane.setBottomAnchor(comments, 0.0);
        AnchorPane.setTopAnchor(comments, 0.0);
        AnchorPane.setLeftAnchor(comments, 0.0);
        AnchorPane.setRightAnchor(comments, 0.0);
        inner.getChildren().add(comments);
        existingChoices = choices;
    }
    
    public ArrayList<String> getFeedback() {
        if (type.getSelectedToggle() == distinct) {
            ArrayList<String> comments = new ArrayList<String>();
            for (int i = 0; i < multiComments.size(); i++) {
                comments.add(multiComments.get(i).getText());
            }
            return comments;
        } else {
            return rightWrong.getFeedback();
        }
    }

    public void setFeedback(ArrayList<String> choices, ArrayList<String> feedback) {
        existingChoices = choices;
        update(choices);
        if (correctIncorrect.selectedProperty().get()) { //correct incorrect feedback
            rightWrong.setFeedback(choices, feedback);
        } else {
            
        }
    }
    
    class MultiFeedBack extends AnchorPane {
        
        public MultiFeedBack(ArrayList<String> choices) {
            VBox content = new VBox();
            ScrollPane scroll = new ScrollPane();
            scroll.setContent(content);
            scroll.setFitToWidth(true);
            AnchorPane.setBottomAnchor(scroll, 0.0);
            AnchorPane.setTopAnchor(scroll, 0.0);
            AnchorPane.setLeftAnchor(scroll, 0.0);
            AnchorPane.setRightAnchor(scroll, 0.0);
            this.getChildren().add(scroll);
            ArrayList<TextArea> al = new ArrayList<TextArea>();
            for (int i = 0; i < choices.size(); i++) {
                if (existingChoices.size() > i && choices.get(i).equals(existingChoices.get(i))
                        && multiComments.size() > i) {
                    content.getChildren().add(multiComments.get(i));
                    al.add(multiComments.get(i));
                } else {
                    TextArea ta = new TextArea();
                    ta.setWrapText(true);
                    ta.setPromptText("Enter feedback for answering:\n"+choices.get(i));
                    al.add(ta);
                    content.getChildren().add(ta);
                }
            }
            multiComments = al;
        }
        public void setFeedback(ArrayList<String> feedback) {
            
        }
    }
}
