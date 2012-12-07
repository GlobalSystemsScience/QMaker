package lhs.qmaker.multiplechoice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import lhs.qmaker.AnswersPanel;
import lhs.qmaker.Controller;
import lhs.qmaker.WrapButton;

public class MultipleChoiceAnswersPanel extends AnswersPanel {
    private static final long serialVersionUID = 1L;
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    int answer = -1;
    Border defaultBorder = new JButton().getBorder();
    /**
     * Create the panel.
     */
    public MultipleChoiceAnswersPanel() {
        int size = 0;
        ArrayList<String> choices = Controller.choices.getChoices();
        for (int i = 0; i < choices.size(); i++) {
            JButton b = new WrapButton(choices.get(i));
            buttons.add(b);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    for (int i = 0; i < buttons.size(); i++) {
                        buttons.get(i).setBorder(defaultBorder);
                    }
                    JButton me = ((JButton) arg0.getSource());
                    me.setBorder(new LineBorder(new Color(0,146,204),5,true));
                    answer = buttons.indexOf(arg0.getSource());
                }
            });
            size += b.getPreferredSize().height;
            super.answers.add(b);
        }
        super.answers.setPreferredSize(new Dimension(super.WIDTH, size));
    }

    @Override
    public ArrayList<String> getAnswers() {
        if (answer == -1)
            return null;
        ArrayList<String> al = new ArrayList<String>();
        al.add(Controller.choices.getChoices().get(answer));
        return al;
    }

    @Override
    public void toChoices() {
        Controller.setPane(Controller.choices);
    }

    @Override
    public void toComments() {
        if (answer == -1)
            return;
        Controller.comments = new MultipleChoiceCommentsPanel();
        Controller.setPane(Controller.comments);
    }

}
