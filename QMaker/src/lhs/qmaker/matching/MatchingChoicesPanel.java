package lhs.qmaker.matching;

import lhs.qmaker.ChoicesPanel;
import lhs.qmaker.Controller;

public class MatchingChoicesPanel extends ChoicesPanel {

	@Override
	public void toAnswers() {
		Controller.answers = new MatchingAnswersPanel();
		Controller.setPane(Controller.answers);
	}

	@Override
	public void toQuestion() {
		Controller.setPane(Controller.question);
	}

}
