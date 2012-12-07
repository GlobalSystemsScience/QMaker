package lhs.qmaker.multiplechoice;

import lhs.qmaker.ChoicesPanel;
import lhs.qmaker.Controller;

public class MultipleChoiceChoicesPanel extends ChoicesPanel {

    @Override
    public void toAnswers() {
        Controller.answers = new MultipleChoiceAnswersPanel();
        Controller.setPane(Controller.answers);
    }

    @Override
    public void toQuestion() {
        Controller.setPane(Controller.question);
    }

}
