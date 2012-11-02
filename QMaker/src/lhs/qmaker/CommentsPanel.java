package lhs.qmaker;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.util.ArrayList;

public abstract class CommentsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    protected JPanel pane;

    /**
     * Create the panel.
     */
    public CommentsPanel() {
        setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(450, 40));
        add(panel, BorderLayout.NORTH);
        
        JButton toAnswers = new JButton("To Answers");
        toAnswers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                toAnswers();
            }
        });
        toAnswers.setBounds(12, 13, 130, 23);
        panel.add(toAnswers);
        
        JButton completeQuestion = new JButton("Complete");
        completeQuestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                completeQuestion();
            }
        });
        completeQuestion.setBounds(308, 13, 130, 23);
        panel.add(completeQuestion);
        
        JButton button_2 = new JButton("Cancel");
        button_2.setBounds(160, 14, 130, 23);
        panel.add(button_2);
        
        pane = new JPanel();
        pane.setLayout(null);
        pane.setPreferredSize(new Dimension(450, 40));
        add(pane);

    }
    public abstract void toAnswers();
    public void completeQuestion() {
    	Controller.completeQuestion();
    }
    public abstract ArrayList<String> getComments();
}
