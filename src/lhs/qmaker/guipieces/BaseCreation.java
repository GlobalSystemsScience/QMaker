package lhs.qmaker.guipieces;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lhs.qmaker.MainView;
import lhs.qmaker.QuestionCompleteDialog;
import lhs.qmaker.database.Database;

/**
 *
 * @author jordan
 */
public abstract class BaseCreation extends AnchorPane {
    
    @FXML protected Choices choicesPane;
    @FXML protected AnchorPane answerPane;
    @FXML protected AnchorPane feedbackPane;
    @FXML protected TextArea questionArea;
    @FXML protected Button submitButton;
    @FXML protected TextField tag1Field;
    @FXML protected TextField tag2Field;
    @FXML protected TextField tag3Field;
    @FXML protected Tab submitPanel;
    @FXML protected Label messageLabel;
    
    public BaseCreation() {
        FXMLLoader fxmlLoader = new FXMLLoader(BaseCreation.class.getResource("BaseCreation.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    /*
     * called when a creation panel is being fed info from an old question
     */
    public void SetFields(String question, ArrayList<String> choices,
            ArrayList<String> answers, ArrayList<String> feedback, ArrayList<String> tags) {
        questionArea.setText(question);
        setChoices(choices);
        setAnswers(choices, answers);
        setFeedback(choices, feedback);
        setTags(tags);
    }
    
    public void setTags(ArrayList<String> tags) {
        switch (tags.size()) {
            case 3: tag3Field.setText(tags.get(2));
            case 2: tag2Field.setText(tags.get(1));
            case 1: tag1Field.setText(tags.get(0));
        }
    }
    public abstract void setChoices(ArrayList<String> choices);
    public abstract void setAnswers(ArrayList<String> choices, ArrayList<String> answers);
    public abstract void setFeedback(ArrayList<String> choices, ArrayList<String> feedback);
    
    @FXML
    public void numChoiceChange(ActionEvent e) {
        ComboBox cb = (ComboBox) e.getSource();
        int i = Integer.parseInt((String) cb.valueProperty().getValue());
        choicesPane.setNum(i);
        choicesPane.numChange();
    }
    @FXML
    public abstract void answerTabSelected(Event e);
    @FXML
    public abstract void feedbackTabSelected(Event e);
    public abstract ArrayList<String> getAnswers();
    public abstract ArrayList<String> getFeedback();
    public abstract String getType();
    public String getQuestion() {
        return questionArea.getText();
    }
    public ArrayList<String> getChoices() {
        return choicesPane.getChoices();
    }
    public abstract String getQuestionType();
    
    @FXML
    public void submitQuestion() {
        //TODO
        ArrayList<String> answers = getAnswers();
        ArrayList<String> feedback = getFeedback();
        ArrayList<String> choices = getChoices();
        String question = getQuestion();
        String type = getType();
        ArrayList<String> tags = new ArrayList<String>();
        if (tag1Field.getText() != null && !tag1Field.getText().equals("")) {
            tags.add(tag1Field.getText());
        }
        if (tag2Field.getText() != null && !tag2Field.getText().equals("")) {
            tags.add(tag2Field.getText());
        }
        if (tag3Field.getText() != null && !tag3Field.getText().equals("")) {
            tags.add(tag3Field.getText());
        }
        int id = -1;
        if (type.equals(Database.MATCHING)) {
            id = Database.storeMatching(question, choices, answers, feedback, tags);
        } else if (type.equals(Database.MULTICHOICE)) {
            id = Database.storeMultiChoice(question, choices, answers, feedback, tags);
        } else if (type.equals(Database.SELECT)) {
            id = Database.storeSelect(question, choices, answers, feedback, tags);
        } else {
            throw new RuntimeException();
        }
        if (id == -1) {
            //TODO there was an error saving to database
        } else {
            MainView.mainView.setContent(new QuestionCompleteDialog(getEmbedCode(id)));
        }
    }
    public String getEmbedCode(int questionID) {
        //TODO adjust height and width
        String code = "<iframe name=\"QMakerQuestion"+questionID+"\" src=\""
                +Database.QMAKER_URL+"?qid="+questionID
                +"\" width=400 height=400 marginwidth=0 marginheight=0 hspace=0 vspace=0 frameborder=0 scrolling=auto></iframe>";
        System.out.println(code);
        return code;
    }
    @FXML
    public void submitChange() {
        //TODO
        messageLabel.setText("");
        submitButton.setDisable(false);
        ArrayList<String> answers = getAnswers();
        ArrayList<String> feedback = getFeedback();
        ArrayList<String> choices = getChoices();
        String question = getQuestion();
        if (question == null || question.equals("")) {
                submitButton.setDisable(true);
                messageLabel.setText("The question cannot be empty.");
                return;
        }
        if (choices.size() < 1) {
            submitButton.setDisable(true);
            messageLabel.setText("No choices can be left empty.");
            return;
        }
        for (String choice : choices) {
            if (choice == null || choice.equals("")) {
                submitButton.setDisable(true);
                messageLabel.setText("No choices can be left empty.");
                return;
            }
        }
        if (answers.size() < 1) {
            submitButton.setDisable(true);
            messageLabel.setText("No answers can be left empty.");
            return;
        }
        for (String answer : answers) {
            if (answer == null || answer.equals("")) {
                submitButton.setDisable(true);
                messageLabel.setText("No answers can be left empty.");
                return;
            }
        }
        if (feedback.size() < 1) {
            submitButton.setDisable(true);
            messageLabel.setText("No feedback comments can be left empty.");
            return;
        }
        for (String comment : feedback) {
            if (comment == null || comment.equals("")) {
                submitButton.setDisable(true);
                messageLabel.setText("No feedback comments can be left empty.");
                return;
            }
        }
        
    }
}
