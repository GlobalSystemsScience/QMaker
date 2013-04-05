package lhs.qmaker;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lhs.qmaker.database.Database;
import lhs.qmaker.guipieces.BaseCreation;
import lhs.qmaker.matching.MatchingCreation;
import lhs.qmaker.multiplechoice.MultiChoiceCreation;
import lhs.qmaker.select.SelectCreation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Jordan
 */
public class SearchExistingQuestions extends AnchorPane {
    
    @FXML TextField searchField;
    @FXML VBox vbox;
    /*
     * The clone variable is a flag to determine if the selected question should
     * be cloned or edited directly
     */
    public SearchExistingQuestions(boolean clone) {
        FXMLLoader fxmlLoader = new FXMLLoader(SearchExistingQuestions.class.getResource("SearchExistingQuestions.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    @FXML
    public void keyTyped(KeyEvent e) {
        getQuestionsWithTag(searchField.getText());
    }
    
    public void getQuestionsWithTag(String tag) {
        System.out.println(tag);
        JSONArray json = Database.getQuestionsByTag(tag);
        vbox.getChildren().clear();
        for (int i = 0; i < json.length(); i++) {
            try {
                final JSONObject row = json.getJSONObject(i);
                Button b;
                b = new Button(new String(Base64.decode(row.getString("question"))));
                vbox.getChildren().add(b);
                b.setOnAction(new EventHandler<ActionEvent>(){
                    public void handle(ActionEvent t) {
                        try {
                        String q = new String(Base64.decode(row.getString("question")));
                        ArrayList<String> choices = new ArrayList<String>();
                        ArrayList<String> answers = new ArrayList<String>();
                        ArrayList<String> feedback = new ArrayList<String>();
                        JSONArray json = new JSONArray(new String(Base64.decode(row.getString("choices"))));
                        for (int i = 0; i < json.length(); i++) {
                            choices.add(json.getString(i));
                        }
                        json = new JSONArray(new String(Base64.decode(row.getString("answers"))));
                        for (int i = 0; i < json.length(); i++) {
                            answers.add(json.getString(i));
                        }
                        json = new JSONArray(new String(Base64.decode(row.getString("feedback"))));
                        for (int i = 0; i < json.length(); i++) {
                            feedback.add(json.getString(i));
                        }
                        if (row.getString("type").equals("mc")) {//multiplechoice
                            MainView.mainView.setContent(new MultiChoiceCreation(
                                    q, choices, answers, feedback, new ArrayList<String>()));
                        } else if (row.getString("type").equals("ma")) {//matching
                            MainView.mainView.setContent(new MatchingCreation(
                                    q, choices, answers, feedback, new ArrayList<String>()));
                        } else if (row.getString("type").equals("se")) {//select all that apply
                            MainView.mainView.setContent(new SelectCreation(
                                    q, choices, answers, feedback, new ArrayList<String>()));
                        }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (JSONException ex) {
                Logger.getLogger(SearchExistingQuestions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
