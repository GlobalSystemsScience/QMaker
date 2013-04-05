package lhs.qmaker.select;

import java.util.ArrayList;
import javafx.event.Event;
import javafx.scene.layout.AnchorPane;
import lhs.qmaker.database.Database;
import lhs.qmaker.guipieces.BaseCreation;
import lhs.qmaker.guipieces.RightWrongFeedBack;

/**
 *
 * @author jordan
 */
public class SelectCreation extends BaseCreation {

    private AnswerPane answerPane = new AnswerPane();
    private RightWrongFeedBack feedbackPane = new RightWrongFeedBack();
    
    public SelectCreation() {
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
    public SelectCreation(String question, ArrayList<String> choices,
            ArrayList<String> answers, ArrayList<String> feedback, ArrayList<String> tags) {
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
        this.SetFields(question, choices, answers, feedback, tags);
    }
    @Override
    public String getQuestionType() {
        return "se";
    }

    @Override
    public void answerTabSelected(Event e) {
        answerPane.update(choicesPane.getChoices());
    }

    @Override
    public void feedbackTabSelected(Event e) {
        //do nothing
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
        return Database.SELECT;
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
