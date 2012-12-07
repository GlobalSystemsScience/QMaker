package lhs.qmaker;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;


public abstract class QuestionPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private int WIDTH = QMaker.PanelWidth;
    private int HEIGHT = 300;
    private JTextArea question;
    private JButton toChoicesButton;
    private JButton cancelButton;
    private JSplitPane splitPane;
    /**
     * Create the panel.
     */
    public QuestionPanel() {
        setBounds(0, 0, WIDTH, HEIGHT);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerSize(1);
        splitPane.setDividerLocation(45);
        add(splitPane);
        
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH, 40));
        splitPane.setTopComponent(panel);
        panel.setLayout(null);
        
        toChoicesButton = new JButton("To Choices");
        toChoicesButton.setBounds(306, 14, 130, 23);
        toChoicesButton.setPreferredSize(new Dimension(130, 23));
        panel.add(toChoicesButton);
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Controller.goToHomeScreen();
        		Controller.question = null;
        		Controller.choices = null;
        		Controller.answers = null;
        		Controller.comments = null;
        	}
        });
        cancelButton.setBounds(159, 14, 130, 23);
        cancelButton.setPreferredSize(new Dimension(130, 23));
        panel.add(cancelButton);
        toChoicesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                toChoices();
            }
        });
        question = new JTextArea();
        question.setPreferredSize(new Dimension(WIDTH, 400));
        question.setBorder(new LineBorder(new Color(0, 0, 0)));
        question.setFont(new Font("SansSerif", Font.PLAIN, 13));
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setText("Enter Question Here.");
        splitPane.setBottomComponent(question);

    }
    public String getQuestion() {
        return question.getText();
    }
    public abstract void toChoices();
}
