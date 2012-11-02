package lhs.qmaker.MultipleChoice;

import lhs.qmaker.Controller;
import lhs.qmaker.QuestionPanel;

public class MultipleChoiceQuestionPanel extends QuestionPanel {

    @Override
    public void toChoices() {
        Controller.choices = new MultipleChoiceChoicesPanel();
        Controller.setPane(Controller.choices);
    }

}
