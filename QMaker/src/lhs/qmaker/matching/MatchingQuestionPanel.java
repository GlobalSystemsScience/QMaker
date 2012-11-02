package lhs.qmaker.matching;

import lhs.qmaker.Controller;
import lhs.qmaker.QuestionPanel;

public class MatchingQuestionPanel extends QuestionPanel {

	@Override
	public void toChoices() {
		Controller.choices = new MatchingChoicesPanel();
		Controller.setPane(Controller.choices);
	}

}
