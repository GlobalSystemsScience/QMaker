package lhs.qmaker.matching;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import lhs.qmaker.AnswersPanel;
import lhs.qmaker.Controller;

public class MatchingAnswersPanel extends AnswersPanel {
	private ArrayList<String> choices;
	private ArrayList<JTextArea> answers = new ArrayList<JTextArea>();
	public MatchingAnswersPanel() {
		choices = Controller.choices.getChoices();
		for (int i = 0; i < choices.size(); i++) {
			JTextArea ta = new JTextArea("Type answer to match to \"" +choices.get(i)+"\" here.");
			ta.setBorder(new LineBorder(Color.BLACK, 1));
			super.answers.add(ta);
			this.answers.add(ta);
		}
	}

	@Override
	public ArrayList<String> getAnswers() {
		ArrayList<String> answers = new ArrayList<String>();
		for (int i = 0; i < this.answers.size(); i++) {
			answers.add(this.answers.get(i).getText());
		}
		return answers;
	}

	@Override
	public void toChoices() {
		Controller.setPane(Controller.choices);
	}

	@Override
	public void toComments() {
		Controller.comments = new MatchingCommentsPanel();
		Controller.setPane(Controller.comments);
	}


}
