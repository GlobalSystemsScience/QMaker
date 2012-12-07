package lhs.qmaker;

import com.google.gson.JsonObject;
import java.awt.Container;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import lhs.qmaker.matching.MatchingQuestionPanel;
import lhs.qmaker.multiplechoice.MultipleChoiceQuestionPanel;
import lhs.qmaker.select.SelectQuestionPanel;


public class Controller {
    public static final String QUOTATION_MARK_REPLACE = "%%qm%%";
    public static final String LESS_THAN_REPLACE = "%%lt%%";
    public static final String GREATER_THAN_REPLACE = "%%gt%%";
    
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
    public static void goToHomeScreen() {
    	//TODO make an actual home screen
    	setPane(null);
    }
    public static void newMultipleChoice() {
        question = new MultipleChoiceQuestionPanel();
        app.setPane(question);
    }
    public static void newMatching() {
    	question = new MatchingQuestionPanel();
    	app.setPane(question);
    }
    public static void newSelect() {
    	question = new SelectQuestionPanel();
    	app.setPane(question);
    }
    public static void completeQuestion() {
    	String type = "";
    	if (question instanceof MultipleChoiceQuestionPanel) {
    		type = "mc";
    	} else if (question instanceof MatchingQuestionPanel) {
    		type = "ma";
    	} else if (question instanceof SelectQuestionPanel) {
    		type = "se";
    	}/* else if (question instanceof WriteInQuestionPanel) {
    		type = "wi";
    	}*/
    	boolean success = createQuestion(question.getQuestion(),
                choices.getChoices(), answers.getAnswers(), comments.getComments(),type);
        if (success) {
            goToHomeScreen();
            answers = null;
            choices = null;
            comments = null;
            question = null;
        } else {
          //TODO handle cases where success is false
        }
    }
    public static boolean createQuestion(String question, ArrayList<String> choices, ArrayList<String> answers,
            ArrayList<String> comments, String type) {
        try {
          if (comments.size() == 2) {
              // Handles putting the comments into the database.
              String commentids = "";
              for (int i = 0; i < comments.size(); i++) {
                  Queries.sendQuery("INSERT INTO comments (comment) VALUES (\""+format(comments.get(i)) +"\")",false);
                  JsonObject result = Queries.sendQuery("SELECT c.id FROM comments c WHERE c.comment=\""+format(comments.get(i))+"\"",true);
                  String r = result.get("id").getAsString();
                  commentids = commentids+r+",";
              }
              
              /* Handles putting the choices into the database.
               * If it is a matching type question, the answers will also
               * be put into the database as choices.
               */
              String choiceids = "";
              String answerids = "";
              for (int i = 0; i < choices.size(); i++) {
                  Queries.sendQuery("INSERT INTO choices (choice) VALUES (\""+format(choices.get(i))+"\")",false);
                  JsonObject result = Queries.sendQuery("SELECT ch.id FROM choices ch WHERE ch.choice=\""+format(choices.get(i))+"\"",true);
                  String r = result.get("id").getAsString();
                  choiceids = choiceids+r+",";
                  if ((!type.equals("ma")) && answers.contains(choices.get(i))) {
                      answerids = answerids+r+",";
                  } else if (type.equals("ma")) { // The case where the question is matching type
                      // Put all of the answers in as choices here.
                      Queries.sendQuery("INSERT INTO choices (choice) VALUES (\""+format(answers.get(i))+"\")",false);
                      result = Queries.sendQuery("SELECT ch.id FROM choices ch WHERE ch.choice=\""+format(answers.get(i))+"\"",true);
                      r = result.get("id").getAsString();
                      answerids = answerids+r+",";
                  }
              }
              
              /* Put the question tuple into the database that ties the
               * other components together.
               */
              Queries.sendQuery("INSERT INTO questions (question, choice_ids, answer_ids, type, wrong_comment, correct_comment) VALUES (\""+
                      format(question) +"\", \""+choiceids +"\", \""+answerids+"\", \""+type+"\", \""+format(comments.get(1))+"\", \""+format(comments.get(0))+"\")",false);
          } else {
              String commentids[] = new String[comments.size()];
              for (int i = 0; i < comments.size(); i++) {
                  Queries.sendQuery("INSERT INTO comments (comment) VALUES (\""+format(comments.get(i))+"\")",false);
                  JsonObject result = Queries.sendQuery("SELECT c.id FROM comments c WHERE c.comment=\""+format(comments.get(i))+"\"",true);
                  String r = result.get("id").getAsString();
                  commentids[i] = r;
              }
              String answerids = new String();
              String choiceids = new String();
              for (int i = 0; i < choices.size(); i++) {
                  Queries.sendQuery("INSERT INTO choices (choice, comment_id) VALUES (\""+format(choices.get(i))+"\", "+commentids[i]+")",false);
                  JsonObject result = Queries.sendQuery("SELECT ch.id FROM choices ch WHERE ch.choice=\""+format(choices.get(i))+"\" AND ch.comment_id=\""+commentids[i]+"\"",true);
                  String r = result.get("id").getAsString();
                  if (answers.contains(choices.get(i))) {
                      answerids = answerids+r+",";
                  }
                  choiceids = choiceids+r+",";
              }
              Queries.sendQuery("INSERT INTO questions (question, choice_ids, answer_ids, type) VALUES (\""+
                      format(question)+"\", \""+choiceids +"\", \""+answerids+"\", \""+type+"\")",false);
          }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public static String format(String toFormat) {
        String formatted = toFormat.replaceAll("\"", QUOTATION_MARK_REPLACE);
        formatted = formatted.replaceAll("<", LESS_THAN_REPLACE);
        formatted = formatted.replaceAll(">", GREATER_THAN_REPLACE);
        return formatted;
    }
}
