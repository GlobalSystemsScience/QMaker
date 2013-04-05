package lhs.qmaker.multiplechoice;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lhs.qmaker.database.Database;
import lhs.qmaker.guipieces.BaseCreation;

/**
 *
 * @author jordan
 */
public class MultiChoiceCreation extends BaseCreation {
    
    private AnswerPane answerPane = new AnswerPane();
    private FeedbackPane feedbackPane = new FeedbackPane();

    public MultiChoiceCreation() {
        super();
        AnchorPane.setTopAnchor(this.answerPane, 0.0);
        AnchorPane.setLeftAnchor(this.answerPane, 0.0);
        AnchorPane.setRightAnchor(this.answerPane, 0.0);
        AnchorPane.setBottomAnchor(this.answerPane, 0.0);
        super.answerPane.getChildren().add(this.answerPane);
        AnchorPane.setTopAnchor(this.feedbackPane, 0.0);
        AnchorPane.setLeftAnchor(this.feedbackPane, 0.0);
        AnchorPane.setRightAnchor(this.feedbackPane, 0.0);
        AnchorPane.setBottomAnchor(this.feedbackPane, 0.0);
        super.feedbackPane.getChildren().add(this.feedbackPane);
    }
    public MultiChoiceCreation(String question, ArrayList<String> choices,
            ArrayList<String> answers, ArrayList<String> feedback, ArrayList<String> tags) {
        this();
        this.SetFields(question, choices, answers, feedback, tags);
    }
    @Override
    public String getQuestionType() {
        return "mc";
    }

    @Override
    public void answerTabSelected(Event e) {
        ArrayList<String> choices = choicesPane.getChoices();
        answerPane.update(choices);
    }

    @Override
    public void feedbackTabSelected(Event e) {
        feedbackPane.update(choicesPane.getChoices());
    }

    @Override
    public ArrayList<String> getAnswers() {
        return answerPane.getAnswers();
    }

    @Override
    public ArrayList<String> getFeedback() {
        return feedbackPane.getFeedback();
    }

    @Override
    public String getType() {
        return Database.MULTICHOICE;
    }

    @Override
    public void setChoices(ArrayList<String> choices) {
        super.choicesPane.setChoices(choices);
    }

    @Override
    public void setAnswers(ArrayList<String> choices, ArrayList<String> answers) {
        this.answerPane.setAnswers(choices, answers);
    }

    @Override
    public void setFeedback(ArrayList<String> choices, ArrayList<String> feedback) {
        this.feedbackPane.setFeedback(choices, feedback);
    }
    
}
