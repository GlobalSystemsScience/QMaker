package lhs.qmaker.MultipleChoice;

import lhs.qmaker.ChoicesPanel;
import lhs.qmaker.MenuController;

public class MultipleChoiceChoicesPanel extends ChoicesPanel {

    @Override
    public void toAnswers() {
        MenuController.answers = new MultipleChoiceAnswersPanel();
        MenuController.setPane(MenuController.answers);
        
    }

    @Override
    public void toQuestion() {
        MenuController.setPane(MenuController.question);
    }

}
