package lhs.qmaker.MultipleChoice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import lhs.qmaker.AnswersPanel;
import lhs.qmaker.MenuController;
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
        ArrayList<String> choices = MenuController.choices.getChoices();
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
    public String getAnswer() {
        return MenuController.choices.getChoices().get(answer);
    }

    @Override
    public void toChoices() {
        MenuController.setPane(MenuController.choices);
    }

    @Override
    public void toComments() {
        MenuController.comments = new MultipleChoiceCommentsPanel();
        MenuController.setPane(MenuController.comments);
    }

}
