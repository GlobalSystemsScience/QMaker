package lhs.qmaker.select;

import lhs.qmaker.ChoicesPanel;
import lhs.qmaker.Controller;

public class SelectChoicesPanel extends ChoicesPanel {

	@Override
	public void toAnswers() {
		Controller.answers = new SelectAnswersPanel();
		Controller.setPane(Controller.answers);
	}

	@Override
	public void toQuestion() {
		Controller.setPane(Controller.question);
	}


}
