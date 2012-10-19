package lhs.qmaker.MultipleChoice;

import lhs.qmaker.MenuController;
import lhs.qmaker.QuestionPanel;

public class MultipleChoiceQuestionPanel extends QuestionPanel {

    @Override
    public void toChoices() {
        MenuController.choices = new MultipleChoiceChoicesPanel();
        MenuController.setPane(MenuController.choices);
    }

}
