package lhs.qmaker;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JSpinner;

import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public abstract class ChoicesPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final int WIDTH = QMaker.PanelWidth;
    private int HEIGHT = 100;
    private int textAreaHeight = 80;
    private int defaultNumChoices = 4;
    private JSpinner numQs;
    public ArrayList<JTextArea> choices = new ArrayList<JTextArea>();
    private JPanel buttonPanel;
    /**
     * Create the panel.
     */
    public ChoicesPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(WIDTH, 40));
        add(buttonPanel);
        buttonPanel.setLayout(null);
        
        JButton btnNewButton = new JButton("To Question");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toQuestion();
            }
        });
        btnNewButton.setBounds(12, 13, 130, 23);
        buttonPanel.add(btnNewButton);
        
        JButton btnToAnswers = new JButton("To Answers");
        btnToAnswers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toAnswers();
            }
        });
        btnToAnswers.setBounds(308, 13, 130, 23);
        buttonPanel.add(btnToAnswers);
        
        JButton btnNewButton_1 = new JButton("Cancel");
        btnNewButton_1.setBounds(160, 14, 130, 23);
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Controller.goToHomeScreen();
        		Controller.question = null;
        		Controller.choices = null;
        		Controller.answers = null;
        		Controller.comments = null;
        	}
        });
        buttonPanel.add(btnNewButton_1);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH, 40));
        add(panel);
        panel.setLayout(null);
        
        JTextField txtSelectTheNumber = new JTextField();
        txtSelectTheNumber.setOpaque(false);
        txtSelectTheNumber.setBorder(null);
        txtSelectTheNumber.setEditable(false);
        txtSelectTheNumber.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtSelectTheNumber.setText("Select the number of choices");
        txtSelectTheNumber.setBounds(10, 11, 252, 20);
        panel.add(txtSelectTheNumber);
        txtSelectTheNumber.setColumns(10);
        
        numQs = new JSpinner();
        numQs.setModel(new SpinnerNumberModel(new Integer(defaultNumChoices), new Integer(2), null, new Integer(1)));
        numQs.setFont(new Font("SansSerif", Font.PLAIN, 13));
        numQs.setBounds(348, 12, 92, 20);
        numQs.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                createChoices((Integer) numQs.getValue());
            }
        });
        panel.add(numQs);
        createChoices(defaultNumChoices);
    }
    private void createChoices(int num) {
        for (int i = 0; i < choices.size(); i++) {
            this.remove(choices.get(i));
        }
        int i = 0;
        for (; i < num && i < choices.size(); i++) {
            add(choices.get(i));
        }
        for(; i < num; i++) {
            JTextArea textArea = new JTextArea("Type choice " + (i+1) + " here.");
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
            textArea.setPreferredSize(new Dimension(WIDTH, textAreaHeight));
            choices.add(textArea);
            add(textArea);
        }
        HEIGHT = 80 + textAreaHeight * num;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.revalidate();
    }
    public ArrayList<String> getChoices() {
        ArrayList<String> choices = new ArrayList<String>();
        for (int i = 0; i < (Integer)numQs.getValue(); i++) {
            choices.add(this.choices.get(i).getText());
        }
        return choices;
    }
    public abstract void toAnswers();
    public abstract void toQuestion();
}
