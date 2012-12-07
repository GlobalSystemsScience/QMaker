package lhs.qmaker.matching;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import lhs.qmaker.CommentsPanel;
import lhs.qmaker.Controller;
import lhs.qmaker.QMaker;

public class MatchingCommentsPanel extends CommentsPanel {
	JTextArea correct;
	JTextArea incorrect;
	private final int WIDTH = QMaker.PanelWidth;
	private int textAreaHeight = 80;
	/**
	 * Create the panel.
	 */
	public MatchingCommentsPanel() {
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        correct = new JTextArea();
        correct.setText("Type comment for correct answer here.");
        correct.setWrapStyleWord(true);
        correct.setLineWrap(true);
        correct.setBorder(new LineBorder(new Color(0, 0, 0)));
        correct.setPreferredSize(new Dimension(WIDTH, textAreaHeight));
        super.pane.add(correct);
        incorrect = new JTextArea();
        incorrect.setText("Type comment for wrong answer here.");
        incorrect.setWrapStyleWord(true);
        incorrect.setLineWrap(true);
        incorrect.setBorder(new LineBorder(new Color(0, 0, 0)));
        incorrect.setPreferredSize(new Dimension(WIDTH, textAreaHeight));
        super.pane.add(incorrect);
        super.pane.repaint();
        int HEIGHT = 80 + textAreaHeight * 2;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.revalidate();
        pane.revalidate();
	}

	@Override
	public void toAnswers() {
		Controller.setPane(Controller.answers);
	}

	@Override
	public ArrayList<String> getComments() {
		ArrayList<String> comments = new ArrayList<String>();
		comments.add(correct.getText());
		comments.add(incorrect.getText());
		return comments;
	}

}
