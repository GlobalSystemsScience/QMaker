package lhs.qmaker;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.BorderLayout;
import java.util.ArrayList;

public abstract class AnswersPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    protected JPanel answers;

    /**
     * Create the panel.
     */
    public AnswersPanel() {
        setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setAlignmentY(Component.TOP_ALIGNMENT);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(450, 75));
        add(panel, BorderLayout.NORTH);
        
        JButton btnToChoices = new JButton("To Choices");
        btnToChoices.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                toChoices();
            }
        });
        btnToChoices.setBounds(12, 13, 130, 23);
        panel.add(btnToChoices);
        
        JButton btnToComments = new JButton("To Comments");
        btnToComments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toComments();
            }
        });
        btnToComments.setBounds(308, 13, 130, 23);
        panel.add(btnToComments);
        
        JButton button_2 = new JButton("Cancel");
        button_2.setBounds(160, 14, 130, 23);
        panel.add(button_2);
        
        JTextField txtSelectTheCorrect = new JTextField();
        txtSelectTheCorrect.setEditable(false);
        txtSelectTheCorrect.setForeground(Color.BLACK);
        txtSelectTheCorrect.setBorder(null);
        txtSelectTheCorrect.setOpaque(false);
        txtSelectTheCorrect.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtSelectTheCorrect.setText("Select the correct answer to the question.");
        txtSelectTheCorrect.setBounds(12, 50, 336, 20);
        panel.add(txtSelectTheCorrect);
        txtSelectTheCorrect.setColumns(10);
        
        answers = new JPanel();
        answers.setPreferredSize(new Dimension(WIDTH, 500));
        answers.setAlignmentY(Component.TOP_ALIGNMENT);
        add(answers, BorderLayout.CENTER);
        answers.setLayout(new BoxLayout(answers, BoxLayout.Y_AXIS));

    }
    
    public abstract ArrayList<String> getAnswers();
    public abstract void toChoices();
    public abstract void toComments();
}
