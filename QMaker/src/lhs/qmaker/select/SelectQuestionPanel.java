package lhs.qmaker.select;

import lhs.qmaker.Controller;
import lhs.qmaker.QuestionPanel;

public class SelectQuestionPanel extends QuestionPanel {

	@Override
	public void toChoices() {
		Controller.choices = new SelectChoicesPanel();
		Controller.setPane(Controller.choices);
	}

}
