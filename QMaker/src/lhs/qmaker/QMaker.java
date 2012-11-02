package lhs.qmaker;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QMaker {

    private JFrame frame;
    private JScrollPane scrollPane;
    public static int PanelWidth = 450;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QMaker window = new QMaker();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public QMaker() {
        initialize();
    }

    /**
     * Sets the content pane of the application
     */
    public void setPane(Container pane) {
        this.scrollPane.setViewportView(pane);
    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        Controller.setQMaker(this);
        frame = new JFrame();
        frame.setBounds(100, 100, 480, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);
        
        JMenuItem mntmSave = new JMenuItem("Save..");
        mnFile.add(mntmSave);
        
        JMenuItem mntmOpen = new JMenuItem("Open..");
        mnFile.add(mntmOpen);
        
        JMenu mnQuestions = new JMenu("Questions");
        menuBar.add(mnQuestions);
        
        JMenu mnNew = new JMenu("New");
        mnQuestions.add(mnNew);
        
        JMenuItem mntmMultipleChoice = new JMenuItem("Multiple Choice");
        mntmMultipleChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Controller.newMultipleChoice();
            }
        });
        mnNew.add(mntmMultipleChoice);
        
        JMenuItem mntmMatching = new JMenuItem("Matching");
        mntmMatching.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Controller.newMatching();
        	}
        });
        mnNew.add(mntmMatching);
        
        JMenuItem mntmSelectAllThat = new JMenuItem("Select All That Apply");
        mnNew.add(mntmSelectAllThat);
        
        //This is not needed for interactive questions
        /*JMenuItem mntmWriteIn = new JMenuItem("Write In");
        mnNew.add(mntmWriteIn);*/
        
        JMenu mnEdit = new JMenu("Edit");
        mnQuestions.add(mnEdit);
        
        JMenu mnDelete = new JMenu("Delete");
        mnQuestions.add(mnDelete);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(450, 350));
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
}
