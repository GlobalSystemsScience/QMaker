/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lhs.qmaker.database;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

/**
 *
 * @author jordan
 */
public class Database {
    
    public static final String QMAKER_URL = "http://qmaker.zxq.net/qmakerfx/";
    private static final String STORE_QUESTION_URL = "http://qmaker.zxq.net/qmakerfx/storeQuestion.php";
    private static final String GET_QUESTIONS_BY_TAG_URL = "http://qmaker.zxq.net/qmakerfx/getquestionsbytag.php";
    public static final String MULTICHOICE = "mc";
    public static final String MATCHING = "ma";
    public static final String SELECT = "se";
    
    public static int storeMultiChoice(String question, ArrayList<String> choices,
            ArrayList<String> answers, ArrayList<String> feedback, ArrayList<String> tags) {
        return storeQuestion(question, choices, answers, feedback, tags, MULTICHOICE);
    }
    public static int storeMatching(String question, ArrayList<String> choices,
            ArrayList<String> answers, ArrayList<String> feedback, ArrayList<String> tags) {
        return storeQuestion(question, choices, answers, feedback, tags, MATCHING);
    }
    public static int storeSelect(String question, ArrayList<String> choices,
            ArrayList<String> answers, ArrayList<String> feedback, ArrayList<String> tags) {
        return storeQuestion(question, choices, answers, feedback, tags, SELECT);
    }
    
    private static int storeQuestion(String question,
            ArrayList<String> choices, ArrayList<String> answers,
            ArrayList<String> feedback, ArrayList<String> tags, String type) {
        try {
            String queryString = createQueryString(question, choices, answers, feedback, tags, type);
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet get = new HttpGet(STORE_QUESTION_URL+queryString);
            String s = EntityUtils.toString(httpclient.execute(get).getEntity());
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
            warnUserNoNetwork();
        }
        return -1;
    }
    
    private static String createQueryString(String question,
            ArrayList<String> choices, ArrayList<String> answers,
            ArrayList<String> feedback, ArrayList<String> tags, String type) throws UnsupportedEncodingException {
        JSONArray c = new JSONArray(choices);
        JSONArray a = new JSONArray(answers);
        JSONArray f = new JSONArray(feedback);
        JSONArray tgs = new JSONArray(tags);
        String queryString = "?q="+encode(question)
                +"&c="+encode(c.toString())
                +"&a="+encode(a.toString())
                +"&f="+encode(f.toString())
                +"&tags="+encode(tgs.toString())
                +"&t="+type;
        return queryString;
    }
    private static String encode(String s) throws UnsupportedEncodingException {
        return Base64.encode(s.getBytes());
    }
    public static JSONArray getQuestionsByTag(String tag) {
        try {
            String queryString = GET_QUESTIONS_BY_TAG_URL + "?tag="+encode(tag);
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet get = new HttpGet(queryString);
            String s = EntityUtils.toString(httpclient.execute(get).getEntity());
            JSONArray json = new JSONArray(s);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            warnUserNoNetwork();
        }
        return null;
    }
    public static void warnUserNoNetwork() {
        try {
            Parent root = FXMLLoader.load(Database.class.getResource("NoNetwork.fxml"));
        
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
