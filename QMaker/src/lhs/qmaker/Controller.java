package lhs.qmaker;

import java.awt.Container;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import lhs.qmaker.MultipleChoice.MultipleChoiceQuestionPanel;
import lhs.qmaker.matching.MatchingQuestionPanel;


public class Controller {
    public static final String QUOTATION_MARK_REPLACE = "%qm%";
    
    private static final String USERNAME = "qmaker";
    private static final String PASSWORD = "E1nste1n";
    private static final String HOSTNAME = "instance28773.db.xeround.com:18798";
    private static final String DATABASE = "qmaker";
    static QMaker app = null;
    public static QuestionPanel question;
    public static ChoicesPanel choices;
    public static AnswersPanel answers;
    public static CommentsPanel comments;

    public static void setQMaker(QMaker app) {
        Controller.app = app;
    }
    public static void setPane(Container pane) {
        app.setPane(pane);
    }
    public static void newMultipleChoice() {
        question = new MultipleChoiceQuestionPanel();
        app.setPane(question);
    }
    public static void newMatching() {
    	question = new MatchingQuestionPanel();
    	app.setPane(question);
    }
    public static void completeQuestion() {
    	String type = "";
    	if (question instanceof MultipleChoiceQuestionPanel) {
    		type = "mc";
    	} else if (question instanceof MatchingQuestionPanel) {
    		type = "ma";
    	}/* else if (question instanceof SelectQuestionPanel) {
    		type = "se";
    	} else if (question instanceof WriteInQuestionPanel) {
    		type = "wi";
    	}*/
    	boolean success = Controller.createQuestion(Controller.question.getQuestion(),
                Controller.choices.getChoices(), Controller.answers.getAnswers(), Controller.comments.getComments(),type);
        if (success) {
            Controller.setPane(null);//TODO create a start screen pane
            Controller.answers = null;
            Controller.choices = null;
            Controller.comments = null;
            Controller.question = null;
        } else {
          //TODO handle cases where success is false
        }
    }
    public static boolean createQuestion(String question, ArrayList<String> choices, ArrayList<String> answers,
            ArrayList<String> comments, String type) {
        Connection con = null;
        String driver = "org.gjt.mm.mysql.Driver";
        try {
            Class.forName(driver);
          con = DriverManager.getConnection
                  ("jdbc:mysql://" +HOSTNAME+"/"+DATABASE+"?user="+USERNAME+"&password="+PASSWORD); 
          Statement s=con.createStatement();
          if (comments.size() == 2) {
              String commentids = "";
              for (int i = 0; i < comments.size(); i++) {
                  s.executeUpdate("INSERT INTO comments (comment) VALUES (\""+comments.get(i).replaceAll("\"", QUOTATION_MARK_REPLACE) +"\")");
                  s.execute("SELECT c.id FROM comments c WHERE c.comment=\""+comments.get(i).replaceAll("\"", QUOTATION_MARK_REPLACE)+"\"");
                  ResultSet r = s.getResultSet();
                  r.first();
                  commentids = commentids+r.getString(1)+",";
              }
              String choiceids = "";
              String answerids = "";
              for (int i = 0; i < choices.size(); i++) {
                  s.executeUpdate("INSERT INTO choices (choice) VALUES (\""+choices.get(i).replaceAll("\"", QUOTATION_MARK_REPLACE)+"\")");
                  s.execute("SELECT ch.id FROM choices ch WHERE ch.choice=\""+choices.get(i).replaceAll("\"", QUOTATION_MARK_REPLACE)+"\"");
                  ResultSet r = s.getResultSet();
                  r.first();
                  if (answers.contains(choices.get(i))) {
                      answerids = answerids+r.getString(1)+",";
                  }
                  choiceids = choiceids+r.getString(1)+",";
              }
              s.executeUpdate("INSERT INTO questions (question, choice_ids, answer_ids, type, wrong_comment, correct_comment) VALUES (\""+
                      question.replaceAll("\"", QUOTATION_MARK_REPLACE)+"\", \""+choiceids +"\", \""+answerids+"\", \""+type+"\", \""+comments.get(1).replaceAll("\"", QUOTATION_MARK_REPLACE)+"\", \""+comments.get(0).replaceAll("\"", QUOTATION_MARK_REPLACE)+"\")");
          } else {
              //TODO
              //TODO
              //TODO
              String commentids[] = new String[comments.size()];
              for (int i = 0; i < comments.size(); i++) {
                  s.executeUpdate("INSERT INTO comments (comment) VALUES (\""+comments.get(i).replaceAll("\"", QUOTATION_MARK_REPLACE)+"\")");
                  s.execute("SELECT c.id FROM comments c WHERE c.comment=\""+comments.get(i).replaceAll("\"", QUOTATION_MARK_REPLACE)+"\"");
                  ResultSet r = s.getResultSet();
                  r.first();
                  commentids[i] = r.getString(1);
              }
              String answerids = new String();
              String choiceids = new String();
              for (int i = 0; i < choices.size(); i++) {
                  s.executeUpdate("INSERT INTO choices (choice, comment_id) VALUES (\""+choices.get(i).replaceAll("\"", QUOTATION_MARK_REPLACE)+"\", "+commentids[i]+")");
                  s.execute("SELECT ch.id FROM choices ch WHERE ch.choice=\""+choices.get(i).replaceAll("\"", QUOTATION_MARK_REPLACE)+"\" AND ch.comment_id=\""+commentids[i]+"\"");
                  ResultSet r = s.getResultSet();
                  r.first();
                  if (answers.contains(choices.get(i))) {
                      answerids = answerids+r.getString(1)+",";
                  }
                  choiceids = choiceids+r.getString(1)+",";
              }
              s.executeUpdate("INSERT INTO questions (question, choice_ids, answer_ids, type) VALUES (\""+
                      question.replaceAll("\"", QUOTATION_MARK_REPLACE)+"\", \""+choiceids +"\", \""+answerids+"\", \""+type+"\")");
          }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {con.close();} catch (SQLException e) {e.printStackTrace();}
            return false;
        }
        //TODO connect to and use sql database
        //TODO handle failures
        try {con.close();} catch (SQLException e) {e.printStackTrace();}
        return true;
    }
}
