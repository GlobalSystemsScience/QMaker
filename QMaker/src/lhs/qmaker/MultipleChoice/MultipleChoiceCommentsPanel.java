package lhs.qmaker.MultipleChoice;


import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import lhs.qmaker.CommentsPanel;
import lhs.qmaker.MenuController;
import lhs.qmaker.QMaker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

public class MultipleChoiceCommentsPanel extends CommentsPanel {
    private static final long serialVersionUID = 5410283174027876868L;
    private JToggleButton toggleButton;
    private int textAreaHeight = 80;
    private final int WIDTH = QMaker.PanelWidth;
    private int HEIGHT = 100;
    private ArrayList<JTextArea> perChoice = new ArrayList<JTextArea>();
    private ArrayList<JTextArea> correctIncorrect = new ArrayList<JTextArea>();

    /**
     * Create the panel.
     */
    public MultipleChoiceCommentsPanel() {
        super();
        
        toggleButton = new JToggleButton("Comments for Every Choice");
        toggleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (toggleButton.isSelected()) {
                    toggleButton.setText("Correct and Incorrect Comments");
                    createComments();
                } else {
                    toggleButton.setText("Comments for Every Choice");
                    createComments();
                }
            }
        });
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(toggleButton);
        createComments();
    }
    @Override
    public void toAnswers() {
        MenuController.setPane(MenuController.answers);
    }
    @Override
    public void completeQuestion() {
        boolean success = MenuController.createQuestion(MenuController.question.getQuestion(),
                MenuController.choices.getChoices(), MenuController.answers.getAnswers(), MenuController.comments.getComments(),"mc");
        if (success) {
            MenuController.setPane(null);//TODO create a start screen pane
            MenuController.answers = null;
            MenuController.choices = null;
            MenuController.comments = null;
            MenuController.question = null;
        } else {
          //TODO handle cases where success is false
        }
    }
    @Override
    public ArrayList<String> getComments() {
        ArrayList<JTextArea> comments;
        if (toggleButton.isSelected())
            comments = perChoice;
        else
            comments = correctIncorrect;
        ArrayList<String> results = new ArrayList<String>();
        for (int i = 0; i < comments.size(); i++) {
            results.add(comments.get(i).getText());
        }
        return results;
    }
    private void createComments() {
        ArrayList<JTextArea> comments;
        int num = 0;
        if (toggleButton.isSelected()) {
            num = MenuController.choices.getChoices().size();
            comments = perChoice;
        } else {
            num = 2;
            comments = correctIncorrect;
        }
        pane.removeAll();
        pane.add(toggleButton);
        pane.repaint();
        int i = 0;
        for (; i < num && i < comments.size(); i++) {
            pane.add(comments.get(i));
        }
        for(; i < num; i++) {
            JTextArea textArea = new JTextArea();
            if (num == 2) {
                textArea.setText("Type comment for correct answer here.");
            } else {
                textArea.setText("Type comment for answer, \"" + MenuController.choices.getChoices().get(i) + "\", here.");
            }
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
            textArea.setPreferredSize(new Dimension(WIDTH, textAreaHeight));
            comments.add(textArea);
            pane.add(textArea);
        }
        HEIGHT = 80 + textAreaHeight * num;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.revalidate();
    }
}
